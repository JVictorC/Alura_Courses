//
//  ViagemViewModel.swift
//  aluraViagens
//
//  Created by Joao Victor on 13/09/22.
//

import Foundation

enum TravelViewModelType: String {
    case destaques
    case ofertas
    case internacionais
}

protocol TravelViewModel {
    var sessionTitle: String {get}
    var type: TravelViewModelType { get }
    var travels: [Travel] {get set}
    var numberOfRows: Int {get}
    
}
