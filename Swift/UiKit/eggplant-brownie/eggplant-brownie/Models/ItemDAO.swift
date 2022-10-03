//
//  ItemDAO.swift
//  eggplant-brownie
//
//  Created by Joao Victor on 11/09/22.
//

import Foundation


class ItemDao {
    
    func recupaDiretorio() -> URL? {
        guard let diretorio = FileManager.default.urls(
            for: .documentDirectory, in: .userDomainMask
        ).first else { return nil }
        
        
        let path = diretorio.appendingPathComponent("itens")
        
        
        return path
    }
    
    func saveData(_ itens: [Item]) {
        
        guard let path = recupaDiretorio() else { return }
        
        do {
            let data = try NSKeyedArchiver.archivedData(
                withRootObject: itens,
                requiringSecureCoding: false
            )
            
            try data.write(to: path)
        } catch { print(error.localizedDescription) }
        
        
    }
    
    func getData() -> [Item] {
        guard let path = recupaDiretorio() else { return [] }
        
        do {
            let data = try Data(contentsOf: path)
            
            guard let saveItens = try NSKeyedUnarchiver.unarchiveTopLevelObjectWithData(
                data
            ) as? [Item] else { return [] }
            
            return saveItens
            
        } catch { print(error.localizedDescription) }
        
        
        return []
    }
    
    
}
