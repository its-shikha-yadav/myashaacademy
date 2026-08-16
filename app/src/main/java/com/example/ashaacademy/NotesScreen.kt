package com.example.ashaacademy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.remember
@Composable
fun NotesScreen(
    onBack: () -> Unit
) {
    var notesText by remember { mutableStateOf("Loading notes...") }

    LaunchedEffect(Unit) {
        FirebaseFirestore.getInstance()
            .collection("users")
            .document("QLCsaoru6vO4d40WGY8I2oqQGsG2")
            .collection("courses")
            .document("course 1")
            .get()
            .addOnSuccessListener { document ->
                notesText = document.getString("notes") ?: "No notes available"
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
                    contentDescription = "Back"
                )
            }

            Text(
                text = "Chapter 1 Notes",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

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
                    text = "Python Notes",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = notesText,
                    style =
                        MaterialTheme.typography.bodyLarge)


            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Actions",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(
                imageVector = Icons.Default.Download,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Download Notes")
        }

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Share Notes")

        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(
                imageVector = Icons.Default.Bookmark,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Bookmark")

        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "ASHA ACADEMY",
            style = MaterialTheme.typography.bodyMedium
        )

    }

}