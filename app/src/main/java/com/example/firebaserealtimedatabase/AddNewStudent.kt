package com.example.firebaserealtimedatabase

import android.content.Intent
import com.example.firebaserealtimedatabase.databinding.ActivityAddNewStudentBinding
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class AddNewStudent : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityAddNewStudentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference(firebaseAuth.currentUser!!.uid)


        binding.addStudent.setOnClickListener{
            val userId = database.push().key ?: ""
            val user = DataModel(userId, binding.studentName.text.toString(), binding.contactNumber.text.toString(), binding.subjectName.text.toString(),binding.salary.text.toString())
            addDataToFirebase(userId, user)
        }

    }
    fun addDataToFirebase(id : String, user: DataModel){
        database.child(id).setValue(user).addOnSuccessListener{
            Toast.makeText(applicationContext, "Add student Successfully..", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(applicationContext, "Failed..", Toast.LENGTH_SHORT).show()
        }
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }
}