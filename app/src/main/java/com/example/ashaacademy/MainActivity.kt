package com.example.ashaacademy
import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.auth.FirebaseAuth

import com.example.ashaacademy.ui.theme.AshaAcademyTheme
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ashaacademy.HomeScreen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AshaAcademyTheme {
                var showSplash by remember { mutableStateOf(true) }

                if (showSplash) {

                    SplashScreen()

                    LaunchedEffect(Unit) {
                        delay(2000)
                        showSplash = false
                    }

                } else {

                    Greeting(name = "Android")

                }
            }

        }
    }






            }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var email by remember{ mutableStateOf(" ") }
    var password by remember{ mutableStateOf(" ") }
    var isLoggedIn by remember{ mutableStateOf(value = false)}
    val navController = rememberNavController()
    var showSignup by remember { mutableStateOf( false) }
    var showForgetPassword by remember { mutableStateOf( false) }
    var showResetPassword by remember { mutableStateOf(false) }
    if ( isLoggedIn) {
        HomeScreen(
        onLogout = {
            isLoggedIn = false
        }
        )
        return

    }

    if ( showSignup) {
        SignupScreen(
            onRegister = {
                showSignup = false
            }
        )
        return


    }
    if ( showForgetPassword){
        OtpVerificationScreen(
            onPasswordResetComplete = {
                showForgetPassword = false
            }
        )
        return

    }


    Column(
      modifier = modifier
          .fillMaxSize()
          .padding( 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "ASHA ACADEMY"
        )
        OutlinedTextField(
        value = email,
            onValueChange = {email = it },
            label = { Text("Email or Phone Number") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )

        TextButton(
            onClick = {
                showForgetPassword = true

            }
        ) {
            Text("Forget Password")
        }




        Button(onClick = {
            email = email. trim()
            password = password.trim()
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        isLoggedIn = true
                    } else {
                        android.util.Log.e(
                            "LOGIN_ERROR",
                            task.exception?.message.toString()
                        )


                    }
                }

        } ) {
            Text("Login")
            }


         Spacer(modifier =
        Modifier. padding(8.dp))
        TextButton(
            onClick = {
                showSignup = true
            }
        ) {
            Text("Sign Up")
        }


    }





        }









@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AshaAcademyTheme {
        var showSplash by remember { mutableStateOf(true) }

        if (showSplash) {

            SplashScreen()

            LaunchedEffect(Unit) {
                delay(2000)
                showSplash = false
            }

        } else {

            Greeting(name = "Android")

        }
    }
    }





