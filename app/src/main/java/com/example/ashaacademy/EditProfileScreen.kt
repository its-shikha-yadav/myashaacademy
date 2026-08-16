package com.example.ashaacademy

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun EditProfileScreen(
    onBack: () -> Unit
) {

    var name by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(true) }
    var isSaving by remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    val user = auth.currentUser

    // Firebase se purana profile data load karna
    LaunchedEffect(user?.uid) {

        if (user != null) {

            db.collection("users")
                .document(user.uid)
                .get()
                .addOnSuccessListener { document ->

                    if (document.exists()) {

                        name = document.getString("name") ?: ""
                        mobile = document.getString("mobile") ?: ""

                    }

                    isLoading = false
                }
                .addOnFailureListener {

                    isLoading = false
                }

        } else {

            isLoading = false

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        // Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )

            }

            Text(
                text = "Edit Profile",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        if (isLoading) {

            // Loading
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator()

            }

        } else {

            // Student Name
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text("Student Name")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // Mobile Number
            OutlinedTextField(
                value = mobile,
                onValueChange = {
                    mobile = it
                },
                label = {
                    Text("Mobile Number")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            // Save Button
            Button(
                onClick = {

                    if (user != null) {

                        isSaving = true

                        val profileData = hashMapOf(
                            "name" to name,
                            "mobile" to mobile
                        )

                        db.collection("users")
                            .document(user.uid)
                            .set(
                                profileData
                            )
                            .addOnSuccessListener {

                                isSaving = false

                                // Save hone ke baad wapas
                                onBack()
                            }
                            .addOnFailureListener {

                                isSaving = false
                            }
                    }

                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isSaving
            ) {

                if (isSaving) {

                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )

                } else {

                    Text("Save")

                }
            }
        }
    }
}