//
//  CelulaViagemVIew.swift
//  alura-viagens
//
//  Created by Joao Victor on 19/10/22.
//

import SwiftUI

struct CelulaViagemView: View {
    let viagem: Viagem
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(viagem.titulo)
              
            Image(viagem.imagem)
                .resizable()
                .frame(height: 105)
            
            HStack {
                Text(viagem.quantidadeDeDias)
                    .offset(x: 25)
                
                Spacer()
                
                Text(viagem.valor)
                    .offset(x: -25)
            }
        }
    }
}

struct CelulaViagemView_Previews: PreviewProvider {
    static var previews: some View {
        CelulaViagemView(viagem: viagens[0])
            .previewLayout(
                .fixed(width: 400, height: 220)
            )
    }
}
