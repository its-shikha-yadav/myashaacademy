package com.example.ashaacademy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UploadNotesScreen(
    onBack: () -> Unit
) {

    var notesTitle by remember {
        mutableStateOf("")
    }

    var notesDescription by remember {
        mutableStateOf("")
    }

    val notesList = remember {
        mutableStateListOf<String>()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Upload Notes",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = notesTitle,
            onValueChange = {
                notesTitle = it
            },
            label = {
                Text("Notes Title")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = notesDescription,
            onValueChange = {
                notesDescription = it
            },
            label = {
                Text("Notes Description")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                if (
                    notesTitle.isNotEmpty() &&
                    notesDescription.isNotEmpty()
                ) {

                    notesList.add(notesTitle)

                    notesTitle = ""
                    notesDescription = ""

                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(
                Icons.Default.NoteAdd,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Upload Notes")

        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(notesList) { note ->
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
                            text = note,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Button(
                            onClick = {

                                notesList.remove(note)

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