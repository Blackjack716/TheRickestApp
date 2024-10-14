package com.example.therickestapp.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.GetAllCharactersUseCase
import com.example.domain.model.CharacterItem
import com.example.therickestapp.ui.compose.CharacterEvent
import com.example.therickestapp.ui.compose.CharacterListState
import com.example.therickestapp.ui.compose.ListType
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

    val listType = MutableStateFlow(ListType.AllcharacterList)

    val state = MutableStateFlow(CharacterListState(characterList.value, listType.value))

    init {
        viewModelScope.launch {
            getAllCharactersUseCase.execute().distinctUntilChanged().collect {
                _characterList.emit(it)
            }
        }
    }

    fun onEvent(event: CharacterEvent) {
        when (event) {
            is CharacterEvent.OnFavCharacter -> {  }
            CharacterEvent.OnAllListClicked -> TODO()
            CharacterEvent.OnFavListClicked -> TODO()
        }
    }
}