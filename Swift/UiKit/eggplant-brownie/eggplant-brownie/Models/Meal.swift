//
//  meal.swift
//  eggplant-brownie
//
//  Created by Joao Victor on 22/08/22.
//

import UIKit

class Meal {
    var nome: String
    var felicidade: Int
    var itens: [Item]
    
    
    init(name: String, felicidade: Int, itens: [Item]) {
        self.nome = name
        self.felicidade = felicidade
        self.itens = itens
    }
    
    
    func totalCalorias() -> Double {
        var total = 0.0
        
        
        for item in itens {
            total += item.calorias
        }
        
        return total
    }
    
    func details() -> String {
        
        var   messagem = "Felicidade: \(self.felicidade)"
        
        for item in itens {
            messagem += ", \(item.nome) - calorias: \(item.calorias)"
        }
        
        return messagem
    }
    
}
