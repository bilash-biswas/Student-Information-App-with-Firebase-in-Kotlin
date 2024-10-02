package com.example.firebaserealtimedatabase

import android.content.Intent
import com.example.firebaserealtimedatabase.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.addStudent.setOnClickListener{
            startActivity(Intent(applicationContext, AddNewStudent::class.java))
        }

        binding.allStudent.setOnClickListener{
            startActivity(Intent(applicationContext, ShowStudent::class.java))
        }

        binding.signOut.setOnClickListener{
            firebaseAuth.signOut()
            startActivity(Intent(applicationContext, LoginPage::class.java))
            finish()
        }


    }
}