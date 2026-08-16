package com.example.ashaacademy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CourseDetailsScreen(
    courseName: String,
    onBack: () -> Unit,
    onNotes: () -> Unit,
    onVideos: () -> Unit,
    onQuiz: () -> Unit,
    onPdf: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        IconButton(
            onClick = onBack
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = courseName,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Choose what you want to study"
        )

        Spacer(modifier = Modifier.height(25.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Button(
                    onClick = onNotes,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("📄 Notes")
                }

                Button(
                    onClick = onVideos,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("🎥 Videos")
                }

                Button(
                    onClick = onQuiz,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("📝 Quiz")
                }

                Button(
                    onClick = onPdf,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("📂 PDF")
                }

            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Coming Soon",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(10.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Text("📢 Assignment (Coming Soon)")
                Text("❓ Doubt Support (Coming Soon)")
                Text("🏆 Certificate (Coming Soon)")
                Text("🔥 Live Classes (Coming Soon)")

            }

        }

    }

}