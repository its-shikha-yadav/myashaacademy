package com.example.ashaacademy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.LaunchedEffect
import com.google.firebase.firestore.FirebaseFirestore
data class VideoItem(
    val title: String,
    val duration: String
)

@Composable
fun VideoScreen(
    onBack: () -> Unit
) {

    var selectedChapter by remember {
        mutableStateOf("")
    }
    var videoList by remember {
        mutableStateOf<List<VideoItem>>(emptyList())
    }

    LaunchedEffect(Unit) {
        FirebaseFirestore.getInstance()
            .collection("users")
            .document("QLCsaoru6vO4d40WGY8I2oqQGsG2")
            .collection("courses")
            .document("course 1")
            .get()
            .addOnSuccessListener { document ->
                val videos = document.get("videos") as? List<Map<String, Any>>

                videoList = videos?.map {
                    VideoItem(
                        title = it["title"]?.toString() ?: "",
                        duration = it["duration"]?.toString() ?: ""
                    )
                } ?: emptyList()
            }
    }

    if (selectedChapter.isNotEmpty()) {

        VideoPlayerScreen(
            chapterName = selectedChapter,
            onBack = {
                selectedChapter = ""
            }
        )

        return
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
                text = "Video Lectures",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(videoList) { video ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clickable {
                            selectedChapter = video.title
                        },
                    shape = RoundedCornerShape(18.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.PlayCircle,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = video.title,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
 
                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Duration : ${video.duration}",
                                style = MaterialTheme.typography.bodyMedium
                            )

                        }

                        Button(
                            onClick = {
                                selectedChapter = video.title
                            }
                        ) {
                            Text("Play")


                        }

                    }
                        }

                    }

                }

            }
        }
