//
//  TravelOfferViewModel.swift
//  aluraViagens
//
//  Created by Joao Victor on 17/09/22.
//

import Foundation


class TravelOfferViewModel: TravelViewModel {
    var sessionTitle: String { return "Ofertas" }
    
    var type: TravelViewModelType { return .ofertas }
    
    var travels: [Travel] = []
    
    var numberOfRows: Int { return 1 }
    
    init(_ travels: [Travel]) {
        self.travels = travels
    }
}
