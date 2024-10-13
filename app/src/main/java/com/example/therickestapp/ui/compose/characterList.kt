package com.example.therickestapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import com.example.therickestapp.R
import com.example.therickestapp.ui.theme.LocalPallet

@Preview
@Composable
fun CharacterList(

) {
    val listState = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .heightIn(min = 100.dp)
            .background(color = LocalPallet.current.backgroundColor)
            .padding(2.dp)
    ) {
        CharacterItem(
            name = "Rick",
            status = "Alive",
            isFavourite = false,
            avatarUrl = ""
        )
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {

        }

    }

}

@Composable
private fun CharacterItem(
    name: String,
    status: String,
    isFavourite: Boolean,
    avatarUrl: String
) {

    var isLoading by remember {
        mutableStateOf(true)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LocalPallet.current.characterItemBackgroundColor)
            .heightIn(min = 60.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .size(80.dp)
                .align(Alignment.CenterVertically)
        ) {
            AsyncImage(
                model = avatarUrl,
                contentDescription = "avatar",
                modifier = Modifier
                    .fillMaxSize()
                    .then(
                        if (isLoading) {
                            Modifier.shimmerLoadingAnimation(
                                isLoadingCompleted = false,
                                isLightModeActive = !isSystemInDarkTheme()
                            )
                        } else {
                            Modifier
                        }
                    ),
                onState = {
                    isLoading = it !is AsyncImagePainter.State.Success
                },
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.TopStart
            )
        }
        Column(
            modifier = Modifier
                .widthIn(min = 100.dp)
                .weight(1f)
        ) {
            Text(
                text = name,
                modifier = Modifier
                    .padding(start = 4.dp, top = 4.dp)
                    .align(Alignment.Start),
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = LocalTextStyle.current.fontFamily,
                    fontWeight = FontWeight.Bold,
                    color = LocalPallet.current.primaryTextColor,
                    textAlign = TextAlign.Center
                ),
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = status,
                modifier = Modifier
                    .padding(start = 4.dp, top = 4.dp)
                    .align(Alignment.Start),
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

        Column(
            modifier = Modifier
                .align(Alignment.Top)
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.End)
                    .size(26.dp)
                    .clickable {

                    }
            ) {
                if (isFavourite) {
                    FavouriteIcon(R.drawable.favourite_star_icon)
                } else {
                    FavouriteIcon(R.drawable.unfavourite_star_icon)
                }
            }
        }

    }
}

@Composable
fun FavouriteIcon(
    imageId: Int
) {

    var isLoading by remember {
        mutableStateOf(true)
    }

    AsyncImage(
        model = imageId,
        contentDescription = "favourite icon",
        modifier = Modifier
            .fillMaxSize()
            .then(
                if (isLoading) {
                    Modifier.shimmerLoadingAnimation(
                        isLoadingCompleted = false,
                        isLightModeActive = !isSystemInDarkTheme()
                    )
                } else {
                    Modifier
                }
            ),
        onState = {
            isLoading = it !is AsyncImagePainter.State.Success
        },
        contentScale = ContentScale.FillWidth,
        alignment = Alignment.TopStart
    )
}