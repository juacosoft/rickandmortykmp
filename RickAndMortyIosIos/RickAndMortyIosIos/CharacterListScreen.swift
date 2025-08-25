//
//  CharacterListScreen.swift
//  RickAndMortyIosIos
//
//  Created by Joaquin Alfonso Martinez on 25/08/25.
//

import SwiftUI
import SharedKit

struct CharacterListScreen: View {
    var viewModel = CharactersViewModel()
    var body: some View {
        VStack(alignment: .leading) {
            Text("Rick and Morty - Characteres")
                .font(.headline)
                .padding(.leading)
            Text("Platform: " + Greetings().greet())
                .font(.caption)
                .padding(.leading)
            Observing(viewModel.charactersState) { state in
                if(state is CharactersResult.Loading) {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle())
                }
                if let charactersResponse = state as? CharactersResult.Success {
                    let characters = charactersResponse.characterResponse.characters
                    if characters.count > 0 {
                        List(characters, id: \.id) { character in
                            CharacterItemView(characterItem: character)
                        }
                    }
                }
                if(state is CharactersResult.Error) {
                    Image(systemName: "globe")
                        .imageScale(.large)
                        .foregroundStyle(.tint)
                    Text("Hello, world!")
                }
            }
        }
    }
}

struct CharacterItemView: View {
    let characterItem: CharacterEntity
    var body: some View {
        HStack(spacing: 0) {
            AsyncImage(url: URL(string: characterItem.image)){ phase in
                switch phase {
                case .empty:
                    ProgressView()
                        .frame(width: 150, height: 150)
                case .success(let image):
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(width: 150, height: 150)
                        .clipped()
                case .failure:
                    Image(systemName: "photo")
                        .resizable()
                        .scaledToFit()
                        .frame(width: 150, height: 150)
                        .foregroundColor(.gray)
                @unknown default:
                    EmptyView()
                        .frame(width: 150, height: 150)
                }
            }
            VStack(alignment: .leading, spacing: 0) {
                            Text(characterItem.name)
                                .fontWeight(.bold)
                            Spacer().frame(height: 4)
                            Text("Specie: \(characterItem.species)")
                                .font(.caption)
                            Spacer().frame(height: 4)
                            Text("Gender: \(characterItem.gender)")
                                .font(.caption)
                            Spacer()
                        }
                        .padding(.vertical, 4)
                        .padding(.horizontal, 10)
                        .frame(maxWidth: .infinity, alignment: .leading)
        }
                
    }
}
