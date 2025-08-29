package com.example.shared.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.domain.entity.CharactersResult
import com.example.shared.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharactersViewModel: ViewModel(), KoinComponent {

    private val getCharactersUseCase: GetCharactersUseCase by inject()

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