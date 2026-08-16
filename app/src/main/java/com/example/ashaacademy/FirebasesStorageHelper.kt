package com.example.ashaacademy

import com.google.firebase.storage.FirebaseStorage

object FirebaseStorageHelper {

    private val storage = FirebaseStorage.getInstance()

    fun getVideoUrl(
        fileName: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        storage.reference
            .child("videos/$fileName")
            .downloadUrl
            .addOnSuccessListener {
                onSuccess(it.toString())
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun getPdfUrl(
        fileName: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        storage.reference
            .child("pdf/$fileName")
            .downloadUrl
            .addOnSuccessListener {
                onSuccess(it.toString())
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }
}