package com.example.therickestapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therickestapp.ui.theme.LocalPallet

@Preview
@Composable
fun MainView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .background(LocalPallet.current.backgroundColor)
                .padding(4.dp)
        )
    ) {
        CategoryBar()
        CharacterList()
    }
}