package com.example.ashaacademy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.material3.ExperimentalMaterial3Api
@OptIn(ExperimentalMaterial3Api:: class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit
) {

    // -----------------------------
    // MAIN SCREEN STATES
    // -----------------------------

    var showCourseScreen by remember {
        mutableStateOf(false)
    }

    var showNotesScreen by remember {
        mutableStateOf(false)
    }

    var showProfileScreen by remember {
        mutableStateOf(false)
    }

    var showEditProfileScreen by remember {
        mutableStateOf(false)
    }

    var showAdminDashboard by remember {
        mutableStateOf(false)
    }


    // -----------------------------
    // ADMIN SCREEN STATES
    // -----------------------------

    var showManageCourse by remember {
        mutableStateOf(false)
    }

    var showManageChapter by remember {
        mutableStateOf(false)
    }

    var showUploadVideo by remember {
        mutableStateOf(false)
    }

    var showUploadPdf by remember {
        mutableStateOf(false)
    }

    var showUploadNotes by remember {
        mutableStateOf(false)
    }

    var showManageQuiz by remember {
        mutableStateOf(false)
    }


    // -----------------------------
    // BOTTOM NAVIGATION
    // -----------------------------

    var selectedBottomItem by remember {
        mutableStateOf<BottomNav>(BottomNav.Home)
    }


    // -----------------------------
    // CURRENT USER
    // -----------------------------

    val user = FirebaseAuth.getInstance().currentUser

    val isAdmin = user?.uid == "QLCsaoru6vO4d40WGY8l2oqG0sG2"


    // =====================================================
    // NOTES SCREEN
    // =====================================================

    if (showNotesScreen) {

        NotesScreen(
            onBack = {
                showNotesScreen = false
                selectedBottomItem = BottomNav.Home
            }
        )

        return
    }


    // =====================================================
    // EDIT PROFILE SCREEN
    // =====================================================

    if (showEditProfileScreen) {

        EditProfileScreen(
            onBack = {
                showEditProfileScreen = false
                showProfileScreen = true
            }
        )

        return
    }


    // =====================================================
    // PROFILE SCREEN
    // =====================================================

    if (showProfileScreen) {

        ProfileScreen(
            onBack = {
                showProfileScreen = false
                selectedBottomItem = BottomNav.Home
            },
            {
                showProfileScreen = false
                showEditProfileScreen = true
            }
        )

        return
    }


    // =====================================================
    // COURSE SCREEN
    // =====================================================

    if (showCourseScreen) {

        CourseScreen(
            onBack = {
                showCourseScreen = false
                selectedBottomItem = BottomNav.Home
            }
        )

        return
    }


    // =====================================================
    // ADMIN DASHBOARD
    // =====================================================

    if (showAdminDashboard) {

        AdminDashboardScreen(

            onBack = {
                showAdminDashboard = false
            },

            onManageCourse = {
                showAdminDashboard = false
                showManageCourse = true
            },

            onManageChapter = {
                showAdminDashboard = false
                showManageChapter = true
            },

            onUploadVideo = {
                showAdminDashboard = false
                showUploadVideo = true
            },

            onUploadPdf = {
                showAdminDashboard = false
                showUploadPdf = true
            },

            onUploadNotes = {
                showAdminDashboard = false
                showUploadNotes = true
            },

            onManageQuiz = {
                showAdminDashboard = false
                showManageQuiz = true
            },

            onLogout = {
                FirebaseAuth.getInstance().signOut()
                onLogout()
            }
        )

        return
    }


    // =====================================================
    // MANAGE COURSE
    // =====================================================

    if (showManageCourse) {

        ManageCourseScreen(
            onBack = {
                showManageCourse = false
                showAdminDashboard = true
            }
        )

        return
    }


    // =====================================================
    // MANAGE CHAPTER
    // =====================================================

    if (showManageChapter) {

        ManageChapterScreen(
            onBack = {
                showManageChapter = false
                showAdminDashboard = true
            }
        )

        return
    }


    // =====================================================
    // UPLOAD VIDEO
    // =====================================================

    if (showUploadVideo) {

        UploadVideoScreen(
            onBack = {
                showUploadVideo = false
                showAdminDashboard = true
            }
        )

        return
    }


    // =====================================================
    // UPLOAD PDF
    // =====================================================

    if (showUploadPdf) {

        UploadPdfScreen(
            onBack = {
                showUploadPdf = false
                showAdminDashboard = true
            }
        )

        return
    }


    // =====================================================
    // UPLOAD NOTES
    // =====================================================

    if (showUploadNotes) {

        UploadNotesScreen(
            onBack = {
                showUploadNotes = false
                showAdminDashboard = true
            }
        )

        return
    }


    // =====================================================
    // MANAGE QUIZ
    // =====================================================

    if (showManageQuiz) {

        ManageQuizScreen(
            onBack = {
                showManageQuiz = false
                showAdminDashboard = true
            }
        )

        return
    }


    // =====================================================
    // HOME SCREEN UI
    // =====================================================

    Scaffold(

        // -----------------------------
        // TOP BAR
        // -----------------------------

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        text = "ASHA ACADEMY"
                    )
                },

                actions = {

                    if (isAdmin) {

                        TextButton(
                            onClick = {
                                showAdminDashboard = true
                            }
                        ) {
                            Text("Admin")
                        }
                    }
                }
            )
        },


        // -----------------------------
        // BOTTOM NAVIGATION
        // -----------------------------

        bottomBar = {

            AppBottomNavigation(

                selectedItem = selectedBottomItem,

                onItemSelected = { item ->

                    selectedBottomItem = item

                    when (item) {

                        BottomNav.Home -> {

                            showCourseScreen = false
                            showNotesScreen = false
                            showProfileScreen = false
                            showAdminDashboard = false
                        }


                        BottomNav.Courses -> {

                            showCourseScreen = true
                        }


                        BottomNav.Notes -> {

                            showNotesScreen = true
                        }


                        BottomNav.Profile -> {

                            showProfileScreen = true
                        }
                    }
                }
            )
        }

    ) { paddingValues ->


        // =================================================
        // HOME CONTENT
        // =================================================

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center
        ) {


            Text(
                text = "👋 Welcome",
                style = MaterialTheme.typography.headlineSmall
            )


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            Text(
                text = "ASHA ACADEMY",
                style = MaterialTheme.typography.headlineLarge
            )


            Spacer(
                modifier = Modifier.height(8.dp)
            )


            Text(
                text = "Learn Anytime • Anywhere",
                style = MaterialTheme.typography.bodyLarge
            )


            Spacer(
                modifier = Modifier.height(30.dp)
            )


            // Small learning button
            // Main navigation bottom bar se bhi hogi.

            Button(

                onClick = {

                    showCourseScreen = true
                    selectedBottomItem = BottomNav.Courses

                },

                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Start Learning →")
            }
        }
    }
}