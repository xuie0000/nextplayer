package dev.anilbeesetti.nextplayer.feature.videopicker

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.anilbeesetti.nextplayer.feature.videopicker.composables.VideoItemsPickerView

@Composable
fun VideoPickerScreen(
    viewModel: VideoPickerViewModel = hiltViewModel(),
    onVideoItemClick: (uri: Uri) -> Unit
) {
    val uiState by viewModel.videoItems.collectAsState()

    VideoPickerScreen(
        uiState = uiState,
        onVideoItemClick = onVideoItemClick
    )
}

@Composable
internal fun VideoPickerScreen(
    uiState: VideoPickerUiState,
    onVideoItemClick: (uri: Uri) -> Unit
) {
    when (uiState) {
        is VideoPickerUiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        is VideoPickerUiState.Success -> {
            VideoItemsPickerView(
                videoItems = uiState.videoItems,
                onVideoItemClick = onVideoItemClick
            )
        }
    }
}