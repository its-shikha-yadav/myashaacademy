package com.example.ashaacademy

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

@Composable
fun AdminLoginScreen(
    onLoginSuccess: () -> Unit
) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Admin Login",
                    fontSize = 28.sp
                )

                Spacer(modifier = Modifier.height(25.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                    },
                    label = {
                        Text("Admin Username")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(15.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    label = {
                        Text("Password")
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(25.dp))
                Button(
                    onClick = {

                        error = ""

                        if (username.isEmpty() || password.isEmpty()) {
                            error = "Please fill all fields"
                        } else {

                            loading = true

                            if (username == "admin" && password == "123456") {

                                loading = false
                                onLoginSuccess()

                            } else {

                                loading = false
                                error = "Invalid Username or Password"

                            }
                        }

                    },
                    modifier = Modifier.fillMaxWidth()
                ) {

                    if (loading) {

                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 3.dp
                        )

                    } else {

                        Text("LOGIN")

                    }

                }

                Spacer(modifier = Modifier.height(15.dp))

                if (error.isNotEmpty()) {

                    Text(
                        text = error,
                        color = Color.Red
                    )

                }


            }

        }

    }
}