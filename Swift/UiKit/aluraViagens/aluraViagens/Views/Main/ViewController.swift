//
//  ViewController.swift
//  aluraViagens
//
//  Created by Joao Victor on 11/09/22.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var tableView: UITableView!

    override func viewDidLoad() {
        super.viewDidLoad()

        
        view.backgroundColor = UIColor(
            red: 39.0/255.0,
            green: 59.0/255.0,
            blue: 119.0/255.0,
            alpha: 1
        )

        configureTableView()
    }
    
    
    func configureTableView() {
        
        tableView.register(
            UINib(nibName: "TravelTableViewCell", bundle: nil),
            forCellReuseIdentifier: "TravelTableViewCell"
        )
        
        tableView.register(
            UINib(nibName: "OfferTableViewCell", bundle: nil),
            forCellReuseIdentifier: "OfferTableViewCell"
        )
       
        tableView.dataSource = self
        tableView.delegate = self
        
        
        tableView.sectionHeaderTopPadding = 0
        
    }
    
    
    func goToDeatails(_ travel: Travel) {
        let detailsController = DetailsViewController.instance(travel)
        
        
        navigationController?.pushViewController(detailsController, animated: true)
    }
    
}


extension ViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let viewModel = sessaoDeViagens?[indexPath.section]
        
        switch viewModel?.type {
        case .destaques, .internacionais:
            guard let selectedTravel = viewModel?.travels[indexPath.row] else { return }
            
            goToDeatails(selectedTravel)
        default:
            break
        }


    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        
        if(section == 0) {
            let headerView = Bundle.main.loadNibNamed(
                "HomeTableViewHeader",
                owner: self
            )?.first as? HomeTableViewHeader
            
            headerView?.configureView()
            
            
            return headerView
        }
        
        return nil
    }
    
    func tableView(
        _ tableView: UITableView,
        heightForHeaderInSection section: Int
    ) -> CGFloat {
        if(section == 0) {
            return 300
        }
        
        return 0

    }
    
}


extension ViewController: UITableViewDataSource {
    func numberOfSections(in tableView: UITableView) -> Int {
        return sessaoDeViagens?.count ?? 0
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return  UIDevice.current.userInterfaceIdiom == .phone
                ? 400
                : 475
    }
        
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
     
        
        let viewModel = sessaoDeViagens?[indexPath.section]
        
        switch viewModel?.type {
        case .destaques:
            guard let travelCell = tableView.dequeueReusableCell(
                withIdentifier: "TravelTableViewCell"
            ) as? TravelTableViewCell else {
                fatalError("error ao criar TravelTableViewCell")
            }
            travelCell.configureCell(viewModel?.travels[indexPath.row])
            return travelCell

        case .ofertas:
            guard let offerCell = tableView.dequeueReusableCell(
                withIdentifier: "OfferTableViewCell"
            ) as? OfferTableViewCell else {
                fatalError("error ao criar TravelTableViewCell")
            }
            
            offerCell.configure(viewModel?.travels)
            offerCell.delegate = self
            
            return offerCell
        default:
            return UITableViewCell()
        }
    }
    
    
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        let teste = sessaoDeViagens?[section].numberOfRows ?? 0
       return teste
    }
}


extension ViewController: OfferTableViewCellDelegate {
    func didSelectedView(_ travel: Travel) {
        self.goToDeatails(travel)
    }
}
