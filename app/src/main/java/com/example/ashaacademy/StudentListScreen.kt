package com.example.ashaacademy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Student(
    val name: String,
    val email: String
)

@Composable
fun StudentListScreen(
    onBack: () -> Unit
) {

    val studentList = listOf(

        Student("Rahul Kumar", "rahul@gmail.com"),
        Student("Shikha Yadav", "shikha@gmail.com"),
        Student("Aman Singh", "aman@gmail.com"),
        Student("Priya Sharma", "priya@gmail.com"),
        Student("Rohit Verma", "rohit@gmail.com")

    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Student List",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(studentList) { student ->
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
                            .padding(16.dp)
                    ) {

                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(15.dp))

                        Column {

                            Text(
                                text = student.name,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = student.email,
                                style = MaterialTheme.typography.bodyMedium
                            )

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