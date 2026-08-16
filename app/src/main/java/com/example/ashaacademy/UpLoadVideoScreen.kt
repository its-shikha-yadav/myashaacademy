package com.example.ashaacademy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID
@Composable
fun UploadVideoScreen(
    onBack: () -> Unit
) {

    var videoTitle by remember {
        mutableStateOf("")
    }

    var videoLink by remember {
        mutableStateOf("")
    }
    var selectedVideoUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val storage = FirebaseStorage.getInstance()
    fun uploadVideo(
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val uri = selectedVideoUri ?: return

        val fileName = "videos/${UUID.randomUUID()}.mp4"

        val ref = storage.reference.child(fileName)

        ref.putFile(uri)
            .addOnSuccessListener {

                ref.downloadUrl.addOnSuccessListener { downloadUrl ->

                    onSuccess(downloadUrl.toString())

                }

            }
            .addOnFailureListener {

                onFailure(it.message ?: "Upload Failed")

            }
    }

    val videoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedVideoUri = uri
    }

    val videoList = remember {

        mutableStateListOf<String>()

    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {

        Text(

            text = "Upload Videos",

            style = MaterialTheme.typography.headlineMedium

        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(

            value = videoTitle,

            onValueChange = {

                videoTitle = it

            },

            label = {

                Text("Video Title")

            },

            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(

            value = videoLink,

            onValueChange = {

                videoLink = it

            },

            label = {

                Text("Video Link")

            },

            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                videoPickerLauncher.launch("video/*")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Choose Video")
        }
        selectedVideoUri?.let {
            Text(
                text = "Video Selected Successfully",
                color = MaterialTheme.colorScheme.primary
            )
        }

        Button(

            onClick = {

                if (videoTitle.isNotEmpty() && selectedVideoUri != null) {

                    uploadVideo(

                        onSuccess = { downloadUrl ->

                            videoLink = downloadUrl

                        },

                        onFailure = { error ->

                            println(error)

                        }

                    )

                }

            },
            modifier = Modifier.fillMaxWidth()

        ) {

            Icon(
                Icons.Default.CloudUpload,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Upload Video")

        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(videoList) { video ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),

                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = video,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Button(

                            onClick = {

                                videoList.remove(video)

                            }

                        ) {

                            Text("Delete")

                        }

                    }

                }

            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            onClick = {

                onBack()

            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Back")

        }

    }

}