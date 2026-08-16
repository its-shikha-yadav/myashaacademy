package com.example.ashaacademy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ManageChapterScreen(
    onBack: () -> Unit
) {

    var chapterName by remember {
        mutableStateOf("")
    }

    val chapterList = remember {

        mutableStateListOf(

            "Chapter 1",
            "Chapter 2",
            "Chapter 3",
            "Chapter 4",
            "Chapter 5"

        )

    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {

        Text(

            text = "Manage Chapters",

            style = MaterialTheme.typography.headlineMedium

        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(

            value = chapterName,

            onValueChange = {

                chapterName = it

            },

            label = {

                Text("Chapter Name")

            },

            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(

            onClick = {

                if (chapterName.isNotEmpty()) {

                    chapterList.add(chapterName)

                    chapterName = ""

                }

            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Icon(Icons.Default.Add, null)

            Spacer(modifier = Modifier.width(8.dp))

            Text("Add Chapter")

        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(chapterList) { chapter ->
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
                            text = chapter,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Row {

                            IconButton(
                                onClick = {

                                    // Edit Chapter
                                    chapterName = chapter

                                }
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit"
                                )

                            }

                            IconButton(
                                onClick = {

                                    chapterList.remove(chapter)

                                }
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete"
                                )

                            }

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

