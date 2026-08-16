package com.example.ashaacademy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val answer: Int
)

@Composable
fun QuizScreen(
    onBack: () -> Unit
) {

    val questions = listOf(

        QuizQuestion(
            question = "Python is developed by?",
            options = listOf(
                "Guido van Rossum",
                "James Gosling",
                "Dennis Ritchie",
                "Bjarne Stroustrup"
            ),
            answer = 0
        ),

        QuizQuestion(
            question = "Which keyword is used for function?",
            options = listOf(
                "fun",
                "define",
                "def",
                "function"
            ),
            answer = 2
        )

    )

    var currentQuestion by remember {
        mutableStateOf(0)
    }

    var selectedOption by remember {
        mutableStateOf(-1)
    }

    var score by remember {
        mutableStateOf(0)
    }

    var showResult by remember {
        mutableStateOf(false)
    }

    if (showResult) {

        ResultScreen(
            score = score,
            total = questions.size,
            onBack = onBack
        )

        return
    }

    val question = questions[currentQuestion]

    Column(
        modifier = Modifier
            .fillMaxSize()
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
                    contentDescription = null
                )

            }

            Text(
                text = "Quiz",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Question ${currentQuestion + 1}/${questions.size}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = question.question,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        question.options.forEachIndexed { index, option ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = selectedOption == index,
                        onClick = {
                            selectedOption = index
                        }
                    )
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                RadioButton(
                    selected = selectedOption == index,
                    onClick = {
                        selectedOption = index
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = option,
                    fontSize = 18.sp
                )

            }

        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                if (selectedOption == question.answer) {
                    score++
                }

                if (currentQuestion < questions.size - 1) {
                    currentQuestion++
                    selectedOption = -1
                } else {
                    showResult = true
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Next")

        }


    }

    @Composable
    fun ResultScreen(
        score: Int,
        total: Int,
        onBack: () -> Unit
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "🎉 Quiz Completed!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Your Score",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "$score / $total",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back to Video")
            }

        }
    }}