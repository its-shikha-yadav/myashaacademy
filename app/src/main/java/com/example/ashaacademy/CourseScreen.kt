package com.example.ashaacademy


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.LaunchedEffect
import java.nio.file.WatchEvent

data class Course(
    val title: String,
    val description: String
)

@Composable
fun CourseScreen(
    onBack: () -> Unit
){

    var search by remember { mutableStateOf("") }
    var showVideoScreen by remember { mutableStateOf(false) }
    var showCourseDetails by remember { mutableStateOf(false) }
    var selectedCourse by remember { mutableStateOf("") }
    var showNotesScreen by remember { mutableStateOf(false) }
    var showQuizScreen by remember { mutableStateOf(false) }
    var showPdfScreen by remember { mutableStateOf(false) }
    var courseList by remember {
        mutableStateOf(
            listOf(
                Course("Course 1", "Class 6 Mathematics Course"),
                Course("Course 2", "Class 7 Mathematics Course"),
                Course("Course 3", "Class 8 Mathematics Course"),
                Course("Course 4", "Class 9 Mathematics Course"),
                Course("Course 5", "Class 10 Mathematics Course")
            )
        )
    }
    if (showVideoScreen) {
        VideoScreen(
            onBack = {
                showVideoScreen = false
            }
        )
        return
    }
    if (showNotesScreen) {
        NotesScreen(
            onBack = {
                showNotesScreen = false
            }
        )
        return
    }

    if (showQuizScreen) {
        QuizScreen(
            onBack = {
                showQuizScreen = false
            }
        )
        return
    }

    if (showPdfScreen) {
        PdfScreen(
            onBack = {
                showPdfScreen = false
            }
        )
        return
    }
    if (showCourseDetails) {

        CourseDetailsScreen(

            courseName = selectedCourse,

            onBack = {
                showCourseDetails = false
            },
            onNotes = {
                showCourseDetails = false
                showNotesScreen = true
            },

            onVideos = {
                showCourseDetails = false
                showVideoScreen = true
            },

            onQuiz = {
                showCourseDetails = false
                showQuizScreen = true
            },

            onPdf = {
                showCourseDetails = false
                showPdfScreen = true
            }

        )

        return
    }
    android.util.Log.d("COURSE_TEST","Course:$courseList")
    val filteredCourses = courseList.filter {
        it.title.contains(search, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {
                Text(
                    text = "‹",
                    fontSize = 32.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = "Explore Courses",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Choose a course and start learning",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = search,
            onValueChange = {
                search = it
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("Search Course")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(filteredCourses) { course ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clickable {

                            selectedCourse = course.title
                            showCourseDetails = true

                        },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    )
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "📚 COURSE",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = course.title,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = course.description,
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 20.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Start learning with videos, notes, quiz and PDF",
                            style = MaterialTheme.typography.bodySmall
                        )


                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {

                            FilledTonalButton(
                                onClick = {
                                    showNotesScreen = true
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("📄 Notes")
                            }

                            FilledTonalButton(
                                onClick = {
                                    showVideoScreen = true
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("🎥 Videos")
                            }

                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {

                            FilledTonalButton(
                                onClick = {
                                    showQuizScreen = true
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("📝 Quiz")
                            }

                            FilledTonalButton(
                                onClick = {
                                    showPdfScreen = true
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("📂 PDF")
                            }

                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                selectedCourse = course.title
                                showCourseDetails = true
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Start Learning  →",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                        }

                    }

                }

            }
        }

    }
}