//
//  AlertCustom.swift
//  eggplant-brownie
//
//  Created by Joao Victor on 03/09/22.
//


import UIKit


class AlertCustom {
    
    let controller: UIViewController
    
    init(controller: UIViewController) {
        self.controller = controller
    }
    
    func show(title: String = "Desculpe",subTitle: String) {
        let alert = UIAlertController(
            title: title,
            message: subTitle,
            preferredStyle: .alert
        )
        
        let ok = UIAlertAction(title: "Ok", style: .cancel)
        
        
        alert.addAction(ok)
        
        
        controller.present(alert, animated: true)
        
    }
}
