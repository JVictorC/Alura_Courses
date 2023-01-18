//
//  Camera.swift
//  Alura Ponto
//
//  Created by Joao Victor Cordeiro da Silva on 10/01/23.
//

import UIKit


class Camera: NSObject {
    
    func abrirCamera(_ controller: UIViewController, _ imagePicker: UIImagePickerController) {
        imagePicker.allowsEditing = true
        imagePicker.sourceType = .camera
        imagePicker.
        
        controller.present(imagePicker, animated: true)
    }
    
}
