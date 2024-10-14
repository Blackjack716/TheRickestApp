package com.example.therickestapp.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.GetAllCharactersUseCase
import com.example.domain.features.GetFavouriteCharactersUseCase
import com.example.domain.features.SetFavouriteCharacterUseCase
import com.example.domain.model.CharacterItem
import com.example.therickestapp.ui.compose.CharacterEvent
import com.example.therickestapp.ui.compose.CharacterListState
import com.example.therickestapp.ui.compose.ListType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val setFavouriteCharacterUseCase: SetFavouriteCharacterUseCase,
    private val getFavouriteCharactersUseCase: GetFavouriteCharactersUseCase
) : ViewModel() {

    private val _characterList = MutableStateFlow<List<CharacterItem>>(emptyList())

    private val _favouriteList = MutableStateFlow<List<CharacterItem>>(emptyList())

    private val listType = MutableStateFlow(ListType.AllCharacterList)

    private val _state = MutableStateFlow(CharacterListState(_characterList.value, listType.value))
    val state = _state.asStateFlow()

    init {
        observeCharacterList()
        observeFavouriteList()
        observeListType()
    }

    private fun observeCharacterList() {
        viewModelScope.launch {
            getAllCharactersUseCase.execute().collectLatest {
                _characterList.emit(it)
            }
        }
    }

    private fun observeFavouriteList() {
        viewModelScope.launch {
            getFavouriteCharactersUseCase.execute().collectLatest {
                _favouriteList.emit(it)
            }
        }
    }

    private fun observeListType() {
        viewModelScope.launch {
            combine(
                listType,
                _characterList,
                _favouriteList,
            ) { listType, characterList, favouriteList ->
                Triple(listType, characterList, favouriteList)
            }.collectLatest { (listType, characterList, favouriteList) ->
                when (listType) {
                    ListType.AllCharacterList -> {
                        _state.emit(CharacterListState(characterList, listType))
                    }
                    ListType.FavouriteList -> {
                        _state.emit(CharacterListState(favouriteList, listType))
                    }
                }
            }
        }
    }


    fun onEvent(event: CharacterEvent) {
        when (event) {
            is CharacterEvent.OnFavCharacterClicked -> {
                setFavouriteCharacterUseCase.execute(event.character, event.isFavourite)
            }
            CharacterEvent.OnAllListClicked -> listType.value = ListType.AllCharacterList
            CharacterEvent.OnFavListClicked -> listType.value = ListType.FavouriteList
        }
    }
}