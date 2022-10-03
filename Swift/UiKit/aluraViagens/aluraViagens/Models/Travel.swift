//
//  Travel.swift
//  aluraViagens
//
//  Created by Joao Victor on 13/09/22.
//

import Foundation

//
//  Viagem.swift
//  AluraViagens
//
//  Created by Ândriu Felipe Coelho on 07/02/21.
//

import Foundation

struct Travel: Codable {
    var id: Int
    var titulo: String
    var asset: String
    var subtitulo: String
    var diaria: Int
    var hospedes: Int
    var precoSemDesconto: Double
    var preco: Double
    var cancelamento: String
    
    static func jsonToData(_ json:[String: Any]) -> Data? {
        return try? JSONSerialization.data(withJSONObject: json, options: [])
    }

    static func decodeJson(_ jsonData: Data) -> Travel? {
        do {
            return try JSONDecoder().decode(Travel.self, from: jsonData)
        } catch {
            print(error.localizedDescription)
            return nil
        }
    }
}
