package com.example.firebaserealtimedatabase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaserealtimedatabase.databinding.ActivityUpdateStudentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateStudent : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateStudentBinding
    private lateinit var studentId : String
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference(firebaseAuth.currentUser!!.uid)

        studentId = intent.getStringExtra("id").toString()
        binding.studentName.setText(intent.getStringExtra("name"))
        binding.subjectName.setText(intent.getStringExtra("subject"))
        binding.contactNumber.setText(intent.getStringExtra("contact"))
        binding.salary.setText(intent.getStringExtra("salary"))

        binding.updateStudent.setOnClickListener{
            updateStudent()
        }

    }
    fun updateStudent(){
        val student = mapOf<String, Any>(
            "studentName" to binding.studentName.text.toString(),
            "contactNumber" to binding.contactNumber.text.toString(),
            "subjectName" to binding.subjectName.text.toString(),
            "salary" to binding.salary.text.toString(),
        )
        database.child(studentId).updateChildren(student).addOnSuccessListener{
            Toast.makeText(applicationContext, "Update Successfully", Toast.LENGTH_SHORT).show()
            finish()
        }.addOnFailureListener{
            Toast.makeText(applicationContext, "Update Failed", Toast.LENGTH_SHORT).show()
        }
    }
}