//
//  DecodableData.swift
//  AluraViagens
//
//  Created by Ândriu Felipe Coelho on 28/01/21.
//

import Foundation

let sessaoDeViagens: [TravelViewModel]? = load("server-response.json")

func load(_ filename: String) -> [TravelViewModel]? {
    let data: Data
    
    guard let file = Bundle.main.url(forResource: filename, withExtension: nil)
        else {
            fatalError("Couldn't find \(filename) in main bundle.")
    }
    
    do {
        data = try Data(contentsOf: file)
    } catch {
        fatalError("Couldn't load \(filename) from main bundle:\n\(error)")
    }
    
    do {
        
        guard let json = try JSONSerialization.jsonObject(with: data, options: []) as? [String: Any] else {
            fatalError("error to read json dictionary")
        }
        
        guard let listaDeViagens = json["viagens"] as? [String: Any] else {
            fatalError("error to read travel list")
        }
        
        guard let jsonData = TravelsTypes.jsonToData(listaDeViagens) else { return nil }
        
        let travelsTypes = TravelsTypes.decodeJson(jsonData)
        
        var listaTravelViewModel: [TravelViewModel] = []
        
        for sessao in listaDeViagens.keys {
            switch TravelViewModelType(rawValue: sessao)  {
            case .destaques:
                if let destaques = travelsTypes?.destaques {
                    let destaqueViewModel = TravelsEmphasisViewModel(destaques)
                    listaTravelViewModel.insert(destaqueViewModel, at: 0)
                }
                
            case .ofertas:
                if let offerList = travelsTypes?.ofertas {
                    let offerViewModel = TravelOfferViewModel(offerList)
                    
                    listaTravelViewModel.append(offerViewModel)
                    
                }
                
                
            default:
                break
            }
        }
        
        return listaTravelViewModel
    } catch {
        fatalError("Couldn't parse")
    }
}
