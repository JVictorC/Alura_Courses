//
//  RemoveMealViewController.swift
//  eggplant-brownie
//
//  Created by Joao Victor on 04/09/22.
//

import UIKit


class RemoveMealViewController {
    let controller: UIViewController
    
    init(controller: UIViewController) {
        self.controller = controller
    }
    
    
    func show(meal: Meal, handler: @escaping (UIAlertAction) -> Void) {
        let alert = UIAlertController(title: meal.nome, message:  meal.details(), preferredStyle: .alert)
        
        let buttonCancel = UIAlertAction(title: "Cancelar", style: .cancel)
        let buttonRemove = UIAlertAction(title: "Remover", style: .destructive, handler:handler)
        
        alert.addAction(buttonCancel)
        alert.addAction(buttonRemove)
        
        
        controller.present(alert, animated: true)
    }
}
