//
//  Item.swift
//  eggplant-brownie
//
//  Created by Joao Victor on 22/08/22.
//

import Foundation

class Item: NSObject {
    var nome: String
    var calorias: Double
    
    required init?(coder: NSCoder) {
        nome = coder.decodeObject(forKey: "nome") as! String
        calorias = coder.decodeDouble(forKey: "calorias")
    }
    
    init(nome: String, calorias: Double) {
        self.nome = nome
        self.calorias = calorias
    }
}


extension Item: NSCoding {
    func encode(with coder: NSCoder) {
        coder.encode(nome, forKey: "nome")
        coder.encode(calorias,forKey: "calorias")
    }
}
