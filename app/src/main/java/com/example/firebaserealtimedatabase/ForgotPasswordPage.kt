package com.example.firebaserealtimedatabase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordPage : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_page)
        firebaseAuth = FirebaseAuth.getInstance()
        val forgotEmail = findViewById<EditText>(R.id.forgotEmail)
        val forgotButton = findViewById<Button>(R.id.resetButton)

        forgotButton.setOnClickListener {
            firebaseAuth.sendPasswordResetEmail(forgotEmail.text.trim().toString())
                .addOnSuccessListener {
                    Utility.toast(
                        applicationContext,
                        "Password reset email send to ${forgotEmail.text.toString()}"
                    )
                    finish()
                }.addOnFailureListener { e ->
                Utility.toast(applicationContext, e.message.toString())
            }
        }
    }
}