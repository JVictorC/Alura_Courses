//
//  TipoDeViagens.swift
//  AluraViagens
//
//  Created by Ândriu Felipe Coelho on 07/02/21.
//

import Foundation

struct TravelsTypes: Codable {
    
    var destaques: [Travel]?
    var ofertas: [Travel]?
    var internacionais: [Travel]?
    
    static func jsonToData(_ json:[String: Any]) -> Data? {
        return try? JSONSerialization.data(withJSONObject: json, options: [])
    }

    static func decodeJson(_ jsonData: Data) -> TravelsTypes? {
        do {
            return try JSONDecoder().decode(TravelsTypes.self, from: jsonData)
        } catch {
            print(error.localizedDescription)
            return nil
        }
    }
}
