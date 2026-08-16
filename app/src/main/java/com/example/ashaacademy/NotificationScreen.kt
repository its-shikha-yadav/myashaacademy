package com.example.ashaacademy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotificationScreen(
    onBack: () -> Unit
) {

    var notificationText by remember {
        mutableStateOf("")
    }

    val notificationList = remember {

        mutableStateListOf<String>()

    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {

        Text(

            text = "Notification",

            style = MaterialTheme.typography.headlineMedium

        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(

            value = notificationText,

            onValueChange = {

                notificationText = it

            },

            label = {

                Text("Write Notification")

            },

            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            onClick = {

                if (notificationText.isNotEmpty()) {

                    notificationList.add(notificationText)

                    notificationText = ""

                }

            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Icon(
                Icons.Default.Notifications,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Send Notification")

        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(notificationList) { notification ->
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
                            text = notification,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.weight(1f)
                        )

                        Button(
                            onClick = {

                                notificationList.remove(notification)

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