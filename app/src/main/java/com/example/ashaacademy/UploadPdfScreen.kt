package com.example.ashaacademy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UploadPdfScreen(
    onBack: () -> Unit
) {

    var pdfTitle by remember {
        mutableStateOf("")
    }

    var pdfLink by remember {
        mutableStateOf("")
    }

    val pdfList = remember {
        mutableStateListOf<String>()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Upload PDF",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = pdfTitle,
            onValueChange = {
                pdfTitle = it
            },
            label = {
                Text("PDF Title")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = pdfLink,
            onValueChange = {
                pdfLink = it
            },
            label = {
                Text("PDF Link")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                if (
                    pdfTitle.isNotEmpty() &&
                    pdfLink.isNotEmpty()
                ) {

                    pdfList.add(pdfTitle)

                    pdfTitle = ""

                    pdfLink = ""

                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(
                Icons.Default.PictureAsPdf,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Upload PDF")

        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(pdfList) { pdf ->
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
                            text = pdf,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Button(
                            onClick = {

                                pdfList.remove(pdf)

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
