package com.example.therickestapp.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.GetAllCharactersUseCase
import com.example.domain.model.CharacterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    private val _characterList = MutableStateFlow<List<CharacterItem>>(emptyList())
    val characterList = _characterList.asStateFlow()

    init {
        viewModelScope.launch {
            getAllCharactersUseCase.execute().distinctUntilChanged().collect {
                _characterList.emit(it)
            }
        }
    }
}