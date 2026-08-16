package com.example.ashaacademy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.compose.BackHandler
data class AdminMenu(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun AdminDashboardScreen(
    onBack: () -> Unit,
    onManageCourse: () -> Unit,
    onManageChapter: () -> Unit,
    onUploadVideo: () -> Unit,
    onUploadPdf: () -> Unit,
    onUploadNotes: () -> Unit,
    onManageQuiz: () -> Unit,
    onLogout: () -> Unit

) {
    BackHandler {
        onBack()
    }

    val menuList = listOf(

        AdminMenu("Manage Courses", Icons.Default.School),
        AdminMenu("Manage Chapters", Icons.Default.Book),
        AdminMenu("Upload Videos", Icons.Default.PlayCircle),
        AdminMenu("Upload PDF", Icons.Default.PictureAsPdf),
        AdminMenu("Upload Notes", Icons.Default.Note),
        AdminMenu("Manage Quiz", Icons.Default.Quiz),
        AdminMenu("Logout", Icons.Default.Logout)

    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Admin Dashboard",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(menuList) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(5.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),

                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = item.icon,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(15.dp))

                        Text(
                            text = item.title,
                            modifier = Modifier.weight(1f),
                            fontSize = 18.sp
                        )

                        Button(

                            onClick = {

                                when (item.title) {

                                    "Manage Courses" -> onManageCourse()

                                    "Manage Chapters" -> onManageChapter()

                                    "Upload Videos" -> onUploadVideo()

                                    "Upload PDF" -> onUploadPdf()

                                    "Upload Notes" -> onUploadNotes()

                                    "Manage Quiz" -> onManageQuiz()

                                    "Logout" -> onLogout()

                                }

                            }

                        ) {

                            Text("Open")

                        }

                    }

                }

            }

        }
        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Dashboard Summary",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text("📚 Courses : 0")
                Text("📖 Chapters : 0")
                Text("🎥 Videos : 0")
                Text("📄 PDFs : 0")
                Text("📝 Notes : 0")
                Text("❓ Quiz : 0")

            }

        }

    }

}