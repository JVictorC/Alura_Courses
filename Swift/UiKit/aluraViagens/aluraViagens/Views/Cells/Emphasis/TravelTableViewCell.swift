//
//  TravelTableViewCell.swift
//  aluraViagens
//
//  Created by Joao Victor on 13/09/22.
//

import UIKit

class TravelTableViewCell: UITableViewCell {

    // MARK - IBOutlets
    @IBOutlet weak var backgroundViewCell: UIView!
    @IBOutlet weak var travelImageView: UIImageView!
    @IBOutlet weak var titleTravelLabel: UILabel!
    @IBOutlet weak var subTitleLabel: UILabel!
    @IBOutlet weak var diaryTravelLalbel: UILabel!
    @IBOutlet weak var priceWithoutDiscountLabel: UILabel!
    @IBOutlet weak var priceWithDiscountLabel: UILabel!
    
    
    func configureCell(_ travel: Travel?) {
        travelImageView.image = UIImage(named: travel?.asset ?? "")
        titleTravelLabel.text = travel?.titulo
        subTitleLabel.text = travel?.subtitulo
        priceWithDiscountLabel.text = "R$ \(travel?.preco ?? 0)"
        
        
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
        
        
        diaryTravelLalbel.text = "\(numberDays) \(diarias) - \(numberHospit) \(hospede)"
        
        
        DispatchQueue.main.async {
            self.backgroundViewCell.addSombra()
        }
        
    }
}
