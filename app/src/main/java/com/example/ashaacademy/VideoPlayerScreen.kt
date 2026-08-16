package com.example.ashaacademy


import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun VideoPlayerScreen(
    chapterName: String,
    onBack: () -> Unit
) {

    var showNotes by remember { mutableStateOf(false) }
    var showQuiz by remember { mutableStateOf(false) }
 var showPdf by remember { mutableStateOf(false) }
    if (showNotes) {
        NotesScreen(
            onBack = {
                showNotes = false
            }
        )
        return
    }

    if (showQuiz) {
        QuizScreen(
            onBack = {
                showQuiz = false
            }
        )
        return
    }
    if (showPdf){
        PdfScreen(
            onBack = {
                showPdf = false
            }
        )
        return
    }

    val context = LocalContext.current

    val player = remember {

        ExoPlayer.Builder(context).build().apply {

            val mediaItem = MediaItem.fromUri(
                Uri.parse(
                    "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
                )
            )

            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true

        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )

            }

            Text(
                text = chapterName,
                fontSize = 22.sp
            )

        }

        Spacer(modifier = Modifier.height(12.dp))

        AndroidView(
            factory = {

                PlayerView(it).apply {
                   this.player = player
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Lecture Options",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        showNotes = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Notes")

                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        showQuiz = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Icon(
                        imageVector = Icons.Default.Quiz,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Start Quiz")

                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = {
                        showPdf = true

                    },
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Icon(
                        imageVector = Icons.Default.PictureAsPdf,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Open PDF")

                }

            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Enjoy your learning 📚",
            style = MaterialTheme.typography.bodyLarge
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }
}


