package com.manuelnunez.apps.features.home.ui

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.manuelnunez.apps.core.ui.component.DynamicAsyncImage
import com.manuelnunez.apps.features.home.ui.HomeScreenViewModel.HomeUiState
import com.manuelnunez.apps.core.ui.R as RUC

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()) {
  val items by viewModel.uiState.collectAsStateWithLifecycle()

  Column {
    OutlinedCard {
      val gifDecoderFactory =
          if (Build.VERSION.SDK_INT >= 28) {
            ImageDecoderDecoder.Factory()
          } else {
            GifDecoder.Factory()
          }
      AsyncImage(
          model =
              ImageRequest.Builder(LocalContext.current)
                  .data(
                      "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExeGJmenh3eGhvYnlxeHQyOHoxaHBsNWdkY3NsNmdhbnNjOXBwZmp5dSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/uo7pY3T68T29HingFY/giphy.gif")
                  .decoderFactory(gifDecoderFactory)
                  .build(),
          placeholder = painterResource(RUC.drawable.ic_broken_image),
          contentScale = ContentScale.Crop,
          modifier = Modifier.clip(CircleShape),
          contentDescription = "") // GIF
    }

    OutlinedCard {
      DynamicAsyncImage(
          imageUrl = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_960_720.jpg",
          contentDescription = null,
          modifier = Modifier.size(140.dp))
    }

    OutlinedCard {
      DynamicAsyncImage(
          imageUrl = "https://images.pexels.com/photos/3573351/pexels-photo-3573351.png",
          contentDescription = null,
          modifier = Modifier.size(140.dp))
    }

    if (items is HomeUiState.Success) {
      LazyColumn {
        items((items as HomeUiState.Success).data) {
          OutlinedCard {
            DynamicAsyncImage(
                imageUrl = it.thumbnailUrl,
                contentDescription = null,
                modifier = Modifier.size(140.dp))
          }
        }
      }
    }
  }
}
