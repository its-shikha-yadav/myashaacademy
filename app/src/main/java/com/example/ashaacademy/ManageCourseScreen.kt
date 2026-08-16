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
fun ManageCourseScreen(
    onBack: () -> Unit
) {

    var courseName by remember { mutableStateOf("") }

    val courseList = remember {

        mutableStateListOf(
            "Python",
            "Java",
            "C Language",
            "C++",
            "HTML"
        )

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Manage Courses",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = courseName,
            onValueChange = {
                courseName = it
            },
            label = {
                Text("Course Name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {

                if (courseName.isNotEmpty()) {

                    courseList.add(courseName)

                    courseName = ""

                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(Icons.Default.Add, null)

            Spacer(modifier = Modifier.width(8.dp))

            Text("Add Course")

        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(courseList) { course ->
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
                            text = course,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Row {

                            IconButton(
                                onClick = {

                                    // Edit Course
                                    courseName = course

                                }
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit"
                                )

                            }

                            IconButton(
                                onClick = {

                                    courseList.remove(course)

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
