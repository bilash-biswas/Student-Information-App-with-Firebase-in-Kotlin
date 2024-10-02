package com.example.firebaserealtimedatabase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaserealtimedatabase.databinding.ActivitySignUpPageBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class SignUpPage : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpPageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUpButton.setOnClickListener{
            signUp()
        }

        binding.loginButton.setOnClickListener{
            startActivity(Intent(applicationContext, LoginPage::class.java))
        }

    }
    private fun signUp(){
        if (binding.signUpEmail.text.isEmpty() || binding.signUpPassword.text.isEmpty() || binding.signUpConformPassword.text.isEmpty()){
            Utility.toast(applicationContext, "Email and Password can't be blank")
            return
        }
        if (binding.signUpPassword.text.isEmpty() != binding.signUpConformPassword.text.isEmpty()){
            Utility.toast(applicationContext, "Password and Conform Password do not match")
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(binding.signUpEmail.text.toString(), binding.signUpPassword.text.toString()).addOnSuccessListener{
            Utility.toast(applicationContext, "Registration successful. Please login.")
            startActivity(Intent(applicationContext, LoginPage::class.java))
            finish()
        }.addOnFailureListener{
            Utility.toast(applicationContext, "Please try again.")
        }
    }
}