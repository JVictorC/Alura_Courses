//
//  TravelsContrastViewModel.swift
//  aluraViagens
//
//  Created by Joao Victor on 13/09/22.
//

import Foundation

class TravelsEmphasisViewModel: TravelViewModel {
    var sessionTitle: String {
        return "Destaques"
        
    }
    
    var type: TravelViewModelType { return .destaques }
    
    var travels: [Travel] = []
    
    var numberOfRows: Int {
        return travels.count
    }
    
    init(_ travels: [Travel]) {
        self.travels = travels
    }
    
    
    
}


