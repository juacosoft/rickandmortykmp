package com.example.rickandmortyapp.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.domain.entity.CharactersResult
import com.example.rickandmortyapp.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel: ViewModel() {

    private val getCharactersUseCase = GetCharactersUseCase.getInstance()

    private var _characterState : MutableStateFlow<CharactersResult> = MutableStateFlow(CharactersResult.Loading)
    val charactersState = _characterState.asStateFlow()

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            _characterState.value = getCharactersUseCase()
        }
    }
}