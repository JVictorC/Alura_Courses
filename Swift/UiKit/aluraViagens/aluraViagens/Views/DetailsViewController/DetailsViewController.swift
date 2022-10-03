//
//  DetailsViewController.swift
//  aluraViagens
//
//  Created by Joao Victor on 19/09/22.
//

import UIKit

class DetailsViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.configureView()
    }


    // MARK: - IBOulets
    @IBOutlet weak var travelImage: UIImageView!
    @IBOutlet weak var titleTravelLabel: UILabel!
    @IBOutlet weak var subTitleTravelLabel: UILabel!
    @IBOutlet weak var diaryTravelLabel: UILabel!
    @IBOutlet weak var priceWithoutDiscountLabel: UILabel!
    
    @IBOutlet weak var priceTravelLabel: UILabel!
    
    
    @IBAction func backButton(_ sender: UIButton) {
        
        navigationController?.popViewController(animated: true)
        
    }
    
    
    // MARK: - Atributos
    var travel: Travel?
    
    
    class func instance(_ travel: Travel) -> DetailsViewController {
        let controller =  DetailsViewController(
            nibName: String(describing: self),
            bundle: nil
        )
        
        controller.travel = travel
        
        return controller
    }
    
    // MARK: - Methodos
    
    func configureView() {
        travelImage.image = UIImage(named: travel?.asset ?? "")
        titleTravelLabel.text = travel?.titulo
        subTitleTravelLabel.text = travel?.subtitulo
        priceWithoutDiscountLabel.text = "R$ \(travel?.preco ?? 0)"
        
        
        let atributeString: NSMutableAttributedString = NSMutableAttributedString(
            string: "R$ \(travel?.precoSemDesconto ?? 0)"
        )
        
        atributeString.addAttribute(
            NSAttributedString.Key.strikethroughStyle,
            value: 1,
            range: NSMakeRange(0, atributeString.length)
        )
        
        priceWithoutDiscountLabel.attributedText = atributeString
        
        guard let numberDays = travel?.diaria, let numberHospit = travel?.hospedes else { return }
        
        let diarias = numberDays == 1 ? "Diária" : "Diárias"
        let hospede = numberHospit == 1 ? "Hospedes" : "Hospede"
        
        
        diaryTravelLabel.text = "\(numberDays) \(diarias) - \(numberHospit) \(hospede)"
        
        
    }
}
