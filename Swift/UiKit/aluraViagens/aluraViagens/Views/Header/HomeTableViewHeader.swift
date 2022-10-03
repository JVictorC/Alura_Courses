//
//  HomeTableViewHeaderController.swift
//  aluraViagens
//
//  Created by Joao Victor on 11/09/22.
//

import Foundation
import UIKit


class HomeTableViewHeader: UIView {
    // MARK - IBOutlets
    
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var headerViewBackGround: HomeTableViewHeader!
    

    @IBOutlet weak var bannerView: UIView!
    
    
    func configureView() {
        headerViewBackGround.backgroundColor = UIColor(
            red: 39.0/255.0,
            green: 59.0/255.0,
            blue: 119.0/255.0,
            alpha: 1
        )
        
        bannerView.layer.cornerRadius = 15
        bannerView.layer.masksToBounds = true
        
        headerViewBackGround.layer.cornerRadius = UIDevice.current.userInterfaceIdiom == .phone ? 500 : 200
        
        headerViewBackGround.layer.maskedCorners = [.layerMinXMaxYCorner, .layerMaxXMaxYCorner]
        
        
        
    }
}
