//
//  OfferTableViewCell.swift
//  aluraViagens
//
//  Created by Joao Victor on 17/09/22.
//

import UIKit


protocol OfferTableViewCellDelegate {
    func didSelectedView(_ travel: Travel)
}

class OfferTableViewCell: UITableViewCell {
    // MARK: - Atributos
    var delegate: OfferTableViewCellDelegate?
    
    // MARK: - IBOutlets
    @IBOutlet var travelImagesViews: [UIImageView]!
    @IBOutlet var travelTitlelabels: [UILabel]!
    @IBOutlet var travelSubTitleLabels: [UILabel]!
    @IBOutlet var priceWithoutDiscountLabels: [UILabel]!
    @IBOutlet var priceWithDiscountLabels: [UILabel]!
    @IBOutlet var backgroundViews: [UIView]!
    
    private var travels: [Travel]?
    
    func configure(_ travels: [Travel]?) {
        self.travels = travels
        
        guard let safeTravelsList = travels else { return }
        
        for index in 0..<safeTravelsList.count {
            setOutlets(index, viagem: safeTravelsList[index])
        }
        
        
        backgroundViews.forEach { backGround in
            backGround.addGestureRecognizer(UITapGestureRecognizer(
                target: self,
                action: #selector(didSelectedView)
            ))
            backGround.addSombra()
        }
    }
    
    @objc func didSelectedView(_ gesture: UIGestureRecognizer) {
        guard let selectedView = gesture.view?.tag else { return }
        guard let selectedTravel = travels?[selectedView] else { return }
        
        delegate?.didSelectedView(selectedTravel)
    }
    
    
    func setOutlets(_ index: Int, viagem: Travel) {
        let imageOutlet = travelImagesViews[index]
        imageOutlet.image = UIImage(named: viagem.asset)
        
        let tituloOutlet = travelTitlelabels[index]
        tituloOutlet.text = viagem.titulo
        
        let subtituloOutlet = travelSubTitleLabels[index]
        subtituloOutlet.text = viagem.subtitulo
        
        let precoSemDescontoOutlet = priceWithoutDiscountLabels[index]
        precoSemDescontoOutlet.text = "A partir de R$ \(viagem.precoSemDesconto)"
        
        let precoOutlet = priceWithDiscountLabels[index]
        precoOutlet.text = "R$ \(viagem.preco)"
    }
    
}
