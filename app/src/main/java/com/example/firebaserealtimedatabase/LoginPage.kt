package com.example.firebaserealtimedatabase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaserealtimedatabase.databinding.ActivityLoginPageBinding
import com.google.firebase.auth.FirebaseAuth

class LoginPage : AppCompatActivity() {
    private lateinit var binding : ActivityLoginPageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.signUpButton.setOnClickListener{
            startActivity(Intent(applicationContext, SignUpPage::class.java))
        }

        binding.loginButton.setOnClickListener{
            login()
        }

        binding.forgotButton.setOnClickListener{
            startActivity(Intent(applicationContext, ForgotPasswordPage::class.java))
        }
    }
    private fun login(){
        if (binding.loginEmail.text.toString().isEmpty() || binding.loginPassword.text.toString().isEmpty()){
            Utility.toast(applicationContext, "Email and Password can't be blank")
            return
        }
        firebaseAuth.signInWithEmailAndPassword(binding.loginEmail.text.trim().toString(), binding.loginPassword.text.trim().toString()).addOnSuccessListener{
            Utility.toast(applicationContext, "Login Successful")
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }.addOnFailureListener{
            e ->
            Utility.toast(applicationContext, e.message.toString())
        }
    }
}