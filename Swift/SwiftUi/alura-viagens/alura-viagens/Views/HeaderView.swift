//
//  HeaderView.swift
//  alura-viagens
//
//  Created by Joao Victor on 19/10/22.
//

import SwiftUI

struct HeaderView: View {
    var body: some View {
        GeometryReader {superView in
            VStack {
                VStack {
                    Text("alura viagens")
                        .foregroundColor(.white)
                        .font(.custom("Avenir Black", size: 20))
                        .padding(.top, 50)
                    Text("ESPECIAL")
                        .foregroundColor(.white)
                        .font(.custom("Avenir Book", size: 20))
                        .frame(
                            maxWidth: .infinity,
                            alignment: .leading
                        )
                        .padding(.leading, 30)
                    
                    Text("BRASIL")
                        .foregroundColor(.white)
                        .font(.custom("Avenir Book", size: 20))
                        .frame(
                            maxWidth: .infinity,
                            alignment: .leading
                        )
                        .padding(.leading, 30)
                    
                    
                }
                
                .frame(
                    width: superView.size.width,
                    height: 180,
                    alignment: .top
                )
                .background(.purple)
                
                
                
                HStack {
                    Button("Hoteis") {
                        
                    }
                    .font( .custom("Avenir Medium", size: 17))
                    .foregroundColor(.white)
                    .frame(width: 100, height: 50)
                    .background(.blue)
                    .overlay(
                        RoundedRectangle(
                            cornerRadius: 10
                        ).stroke(.blue, lineWidth: 10)
                    )
                    .offset(x: 50)
                    
                    
                    Spacer()
                    
                    Button("Pacotes") {
                        
                    }
                    .font( .custom("Avenir Medium", size: 17))
                    .foregroundColor(.white)
                    .frame(width: 100, height: 50)
                    .background(.orange)
                    .overlay(
                        RoundedRectangle(
                            cornerRadius: 10
                        ).stroke(.orange, lineWidth: 10)
                    )
                    .offset(x: -50)
                }
                .offset(y: -25)
            }
        }
    }
}

struct HeaderView_Previews: PreviewProvider {
    static var previews: some View {
        HeaderView()
            .previewLayout(.fixed(width: 400, height: 220))
    }
}
