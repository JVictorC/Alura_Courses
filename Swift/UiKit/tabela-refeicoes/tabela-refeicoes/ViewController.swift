//
//  ViewController.swift
//  tabela-refeicoes
//
//  Created by Joao Victor on 22/08/22.
//

import UIKit

class ViewController: UITableViewController {
    let meals = ["Churros", "Macarrão", "Pizza"];
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return meals.count
    }
}

