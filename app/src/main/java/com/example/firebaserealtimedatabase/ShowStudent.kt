package com.example.firebaserealtimedatabase

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import com.example.firebaserealtimedatabase.databinding.ActivityShowStudentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ShowStudent : AppCompatActivity() {
    private lateinit var binding : ActivityShowStudentBinding
    private lateinit var studentList: ArrayList<DataModel>
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var adapter: StudentAdapter
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        studentList = ArrayList()
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        adapter = StudentAdapter(applicationContext, studentList)
        database = FirebaseDatabase.getInstance().getReference(firebaseAuth.currentUser!!.uid)

        binding.recyclerView.adapter = adapter

        getAllStudent()

    }
    fun getAllStudent(){
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                studentList.clear()
                for(studentSnapshot in snapshot.children){
                    val student = studentSnapshot.getValue(DataModel::class.java)
                    student?.let {
                        studentList.add(it)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Failed to Load Student", Toast.LENGTH_SHORT).show()
            }
        })
    }
}