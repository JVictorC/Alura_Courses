//
//  RefeicoesTableViewController.swift
//  eggplant-brownie
//
//  Created by Joao Victor on 28/08/22.
//

import UIKit


class RefeicaoesTableViewController: UITableViewController {
    var refeicoes = [
        Meal(name: "Macarrão", felicidade: 4, itens: []),
        Meal(name: "Pizza", felicidade: 4, itens: []),
        Meal(name: "Comida Japonesa", felicidade: 5, itens: []),
    ]
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let savesMeals = MealDAO().getData()
        
        refeicoes = savesMeals
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return refeicoes.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cel = UITableViewCell(style: .default, reuseIdentifier: "")
        let longPress = UILongPressGestureRecognizer(
            target: self,
            action: #selector(mostrarDetalhes(_:))
        )
        
        
        let meal = refeicoes[indexPath.row];
        cel.textLabel?.text = meal.nome
        cel.addGestureRecognizer(longPress)
        
        return cel
    }
    
    @objc func mostrarDetalhes(_ gesture: UILongPressGestureRecognizer) {
        if gesture.state == .began {
            guard let viewSelected = gesture.view as? UITableViewCell,
                  let index = tableView.indexPath(for: viewSelected)
            else { return }
            
            let meal = refeicoes[index.row]
            
            RemoveMealViewController(controller: self).show(meal: meal) { _ in
                self.refeicoes.remove(at: index.row)
                self.tableView.reloadData()
            }
            
        }
    }
    
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        guard let viewController = segue.destination as? ViewController else { return }
        
        viewController.addNewMealDelegate = self
    }
    
    
}

extension RefeicaoesTableViewController: AddNewMealDelegate {
    func add(_ meal: Meal) {
        refeicoes.append(meal)
        
        MealDAO().saveData(refeicoes)
        
        tableView.reloadData()
    }
}
