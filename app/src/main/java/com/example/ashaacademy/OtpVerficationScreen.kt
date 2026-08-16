package com.example.ashaacademy

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpVerificationScreen(
    onPasswordResetComplete: () -> Unit
) {

    var otp by remember { mutableStateOf("") }
    var showResetPassword by remember { mutableStateOf( false) }
    if (showResetPassword) {
        ResetPasswordScreen(
            {
                onPasswordResetComplete()
            }
        )
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "OTP Verification",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Enter the 6-digit OTP sent to your mobile number"
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it },
            label = { Text("Enter OTP") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                showResetPassword = true
            }
        ) {
            Text("Verify OTP")
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(
            onClick = { }
        ) {
            Text("Resend OTP")
        }
    }
}
