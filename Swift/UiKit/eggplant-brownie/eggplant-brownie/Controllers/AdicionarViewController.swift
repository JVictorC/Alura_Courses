//
//  AdicionarViewController.swift
//  eggplant-brownie
//
//  Created by Joao Victor on 30/08/22.
//

import UIKit


protocol AdionarItemDelegate {
    func AdicionarItem(_ item: Item)
}


class AdicionarViewController: UIViewController {
    // MARK: Atributos
    
    var delegate: AdionarItemDelegate?
    
    init(delegate: AdionarItemDelegate) {
        self.delegate = delegate
        
        super.init(nibName: "AdicionarViewController", bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    // MARK: @IBOutlet
    @IBOutlet weak var caloriasItem: UITextField!
    @IBOutlet weak var nameItem: UITextField!
    
    // MARK: IBAction
    @IBAction func addNewItem(_ sender: Any) {
        guard
            let name = nameItem?.text,
            let caloriasString = caloriasItem?.text,
            let calorias = Double(caloriasString)
        else { return }
        

            
        
        let item = Item(nome: name, calorias: Double(calorias))
        
        delegate?.AdicionarItem(item)
        navigationController?.popViewController(animated: true)
    }
     
    
}
