//
//  MealDAO.swift
//  eggplant-brownie
//
//  Created by Joao Victor on 11/09/22.
//

import Foundation


class MealDAO {
    func recuperarCaminho() -> URL? {
        guard let diretorio = FileManager.default.urls(
            for: .documentDirectory, in: .userDomainMask
        ).first else { return nil }
        
        
        let path = diretorio.appendingPathComponent("meals")
        
        return path
    }
    
    func saveData(_ listMeal: Array<Meal>) {
        
        guard let path = recuperarCaminho() else { return }
        
        do {
            let data =  try NSKeyedArchiver.archivedData(
                withRootObject: listMeal, requiringSecureCoding: false
            )
            
            try data.write(to: path);
        } catch {
            print(error.localizedDescription)
            
        }
    }
    
    func getData() -> [Meal] {
        guard let path = recuperarCaminho() else { return [] }
        
        do {
            let data = try Data(contentsOf: path)
            
            guard let saveMeals = try NSKeyedUnarchiver.unarchiveTopLevelObjectWithData(
                data
            ) as? [Meal] else { return [] }
            
            
            return saveMeals
            
        } catch {
            print(error.localizedDescription)
        }
        
        return []
    }
    
}
