package com.example.therickestapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.therickestapp.ui.theme.LocalPallet

@Composable
fun MainView(
    state: CharacterListState,
    onEvent: (CharacterEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .background(LocalPallet.current.backgroundColor)
                .padding(4.dp)
        )
    ) {
        CategoryBar()
        CharacterList(state.characters) {
            when (it) {
                is CharacterEvent.OnFavCharacter -> {

                }

                CharacterEvent.OnAllListClicked -> TODO()
                CharacterEvent.OnFavListClicked -> TODO()
            }
        }
    }
}