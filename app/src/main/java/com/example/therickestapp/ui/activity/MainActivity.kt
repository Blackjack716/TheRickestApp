package com.example.therickestapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.therickestapp.ui.compose.CharacterListState
import com.example.therickestapp.ui.compose.MainView
import com.example.therickestapp.ui.theme.TheRickestAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheRickestAppTheme {

                var state by remember {
                    mutableStateOf(CharacterListState(mainViewModel.state.value.characters, mainViewModel.state.value.listId))
                }

                LaunchedEffect(mainViewModel.characterList) {
                    mainViewModel.characterList.collect {
                        state = CharacterListState(it, mainViewModel.listType.value)
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(
                        state = state,
                        onEvent = mainViewModel::onEvent,
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}