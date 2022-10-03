//
//  ViewController.swift
//  eggplant-brownie
//
//  Created by Joao Victor on 20/08/22.
//

import UIKit

protocol AddNewMealDelegate {
    func add(_ meal: Meal)
}

class ViewController: UIViewController {
    
    // MARK: - Atributos
    
    var addNewMealDelegate: AddNewMealDelegate?
    
    var SelectedItens: [Item] = []
    
    
    var itens = [
        Item(nome: "Molho de Tomate", calorias: 40),
        Item(nome: "Queijo", calorias: 40),
        Item(nome: "Molho apimentado", calorias: 40),
        Item(nome: "Manjericão", calorias: 40),
    ]
    
    
    
    // MARK: IBOutlets
    @IBOutlet var nameTextField: UITextField!
    @IBOutlet var happyTextField: UITextField!
    
    @IBOutlet weak var itensTableView: UITableView?
    // MARK: IBAction
    
    @IBAction func addNewMeal() {
        guard let meal = readDataInView() else {
            AlertCustom(controller: self).show(subTitle: "Erro ao Ler os Dados")
            return
        }
        
        addNewMealDelegate?.add(meal)
        
        self.navigationController?.popViewController(animated: true)
        
    }
     
    private func readDataInView() -> Meal? {
        guard let name: String = nameTextField?.text,
              let felicidade: Int = Int(happyTextField?.text ?? "")
        else {
            return nil
        }
        
        let meal = Meal(name: name, felicidade: felicidade, itens: SelectedItens)
        
        return meal
    }
    
    
   
    
}


// MARK: UITableViewDataSource

extension ViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cel = UITableViewCell(style: .default, reuseIdentifier: "")
        
        cel.textLabel?.text = itens[indexPath.row].nome
        
        return cel
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return itens.count
    }
}

// MARK: UITableViewDelegate

extension ViewController: UITableViewDelegate {
    
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        guard let cel = tableView.cellForRow(at: indexPath) else { return }
        let selectedRow = indexPath.row
        let selectedIten = itens[selectedRow]
        
        if cel.accessoryType  == .none {
            cel.accessoryType = .checkmark
            
            self.SelectedItens.append(selectedIten)
        } else {
            cel.accessoryType = .none
            self.SelectedItens.removeAll { item in
                return selectedIten.nome == item.nome
            }
        }
        
        
    }
}


// MARK: ViewCode

extension ViewController {
    
    private func createButton() -> UIBarButtonItem {
        let buttonAddItem = UIBarButtonItem(
            title: "Add Item",
            style: .plain,
            target: self,
            action: #selector(self.handleClickAddItem)
        )
        
        return buttonAddItem
    }
    
    
    override func viewDidLoad() {
        navigationItem.rightBarButtonItem = createButton()
        
        
        itens = ItemDao().getData() 
    }
    
    
    @objc private func handleClickAddItem() {
        let adicionarItensViewController = AdicionarViewController(delegate: self)
        
        
        navigationController?.pushViewController(adicionarItensViewController, animated: true)
        
        
        
    }
    
}


// MARK: AdionarItemDelegate

extension ViewController: AdionarItemDelegate {
    
    func AdicionarItem(_ item: Item) {
        itens.append(item)
        if let safeTableView = itensTableView {
            safeTableView.reloadData()
        } else {
            AlertCustom(controller: self).show(
                subTitle: "Não Foi Possivel Atualizar a Tabela"
            )
        }
        
        ItemDao().saveData(itens)
        
    }
    
    
}
