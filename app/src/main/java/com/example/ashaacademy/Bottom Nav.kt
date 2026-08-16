package com.example.ashaacademy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
sealed class BottomNav(
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNav("Home", Icons.Default.Home)
    object Courses : BottomNav("Courses", Icons.Default.MenuBook)
    object Notes : BottomNav("Notes", Icons.Default.Note)
    object Profile : BottomNav("Profile", Icons.Default.Person)
}
@Composable
fun AppBottomNavigation(
    selectedItem: BottomNav,
    onItemSelected: (BottomNav) -> Unit
) {
    NavigationBar {

        NavigationBarItem(
            selected = selectedItem == BottomNav.Home,
            onClick = {
                onItemSelected(BottomNav.Home)
            },
            icon = {
                Icon(
                    imageVector = BottomNav.Home.icon,
                    contentDescription = "Home"
                )
            },
            label = {
                Text("Home")
            }
        )

        NavigationBarItem(
            selected = selectedItem == BottomNav.Courses,
            onClick = {
                onItemSelected(BottomNav.Courses)
            },
            icon = {
                Icon(
                    imageVector = BottomNav.Courses.icon,
                    contentDescription = "Courses"
                )
            },
            label = {
                Text("Courses")
            }
        )

        NavigationBarItem(
            selected = selectedItem == BottomNav.Notes,
            onClick = {
                onItemSelected(BottomNav.Notes)
            },
            icon = {
                Icon(
                    imageVector = BottomNav.Notes.icon,
                    contentDescription = "Notes"
                )
            },
            label = {
                Text("Notes")
            }
        )

        NavigationBarItem(
            selected = selectedItem == BottomNav.Profile,
            onClick = {
                onItemSelected(BottomNav.Profile)
            },
            icon = {
                Icon(
                    imageVector = BottomNav.Profile.icon,
                    contentDescription = "Profile"
                )
            },
            label = {
                Text("Profile")
            }
        )
    }
}

