//
//  RickAndMortyIosIosApp.swift
//  RickAndMortyIosIos
//
//  Created by Joaquin Alfonso Martinez on 22/08/25.
//

import SwiftUI
import SharedKit

@main
struct RickAndMortyIosIosApp: App {
    
    init() {
        HelperKt.doInitKoin()
    }
    var body: some Scene {
        WindowGroup {
            CharacterListScreen()
        }
    }
}
