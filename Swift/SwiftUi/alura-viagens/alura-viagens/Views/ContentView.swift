//
//  ContentView.swift
//  alura-viagens
//
//  Created by Joao Victor on 02/10/22.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        GeometryReader { superView in
            VStack {
                HeaderView()
                    .frame(
                        width: superView.size.width,
                        height: 220,
                        alignment: .top
                    )
                
                List(viagens) { viagem in
                    CelulaViagemView(viagem: viagem)
                }
            }
        }
         .edgesIgnoringSafeArea([.top,.trailing,.leading])
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
