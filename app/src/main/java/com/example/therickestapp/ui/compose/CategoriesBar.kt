package com.example.therickestapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.therickestapp.R
import com.example.therickestapp.ui.theme.LocalPallet

@Composable
fun CategoryBar(
    onEvent: (CharacterEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 20.dp, max = 60.dp)
            .background(LocalPallet.current.backgroundColor)
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CategoryButton(
            stringResource(id = R.string.main_list_category_name),
            onCategoryClicked = remember {
                {
                    onEvent(CharacterEvent.OnAllListClicked)
                }
            })
        CategoryButton(
            stringResource(id = R.string.favourite_list_category_name),
            onCategoryClicked = remember {
                {
                    onEvent(CharacterEvent.OnFavListClicked)
                }
            })
    }
}

@Composable
private fun CategoryButton(
    text: String,
    onCategoryClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(size = 9.dp))
            .background(color = LocalPallet.current.buttonBackgroundColor)
            .padding(2.dp)
            .clickable {
                onCategoryClicked()
            }
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(4.dp)
                .widthIn(min = 40.dp)
                .align(alignment = Alignment.CenterHorizontally),
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = LocalTextStyle.current.fontFamily,
                fontWeight = FontWeight.Bold,
                color = LocalPallet.current.primaryTextColor,
                textAlign = TextAlign.Center
            ),
            overflow = TextOverflow.Ellipsis
        )
    }
}