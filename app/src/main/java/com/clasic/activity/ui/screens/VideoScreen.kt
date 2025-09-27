package com.clasic.activity.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun VideoScreen() {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(Uri.parse("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"))
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    Column(
        Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidView(
            factory = { PlayerView(it).apply { player = exoPlayer } },
            modifier = Modifier.fillMaxWidth().height(250.dp)
        )

        Spacer(Modifier.height(16.dp))

        Row {
            Button(onClick = { exoPlayer.play() }, Modifier.padding(4.dp)) { Text("Play") }
            Button(onClick = { exoPlayer.pause() }, Modifier.padding(4.dp)) { Text("Pause") }
            Button(onClick = {
                exoPlayer.stop()
                exoPlayer.seekTo(0)
            }, Modifier.padding(4.dp)) { Text("Stop") }
        }
    }
}
