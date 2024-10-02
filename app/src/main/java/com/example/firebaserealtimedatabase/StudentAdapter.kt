package com.example.firebaserealtimedatabase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class StudentAdapter(val context: Context,val mList: ArrayList<DataModel>) :
    RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.student, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val student = mList.get(position)
        holder.name.text = student.studentName
        holder.subject.text = student.subjectName
        holder.contact.text = student.contactNumber
        holder.salary.text = student.salary

        holder.delete.setOnClickListener{
            val firebaseAuth = FirebaseAuth.getInstance()
            val database = FirebaseDatabase.getInstance().getReference(firebaseAuth.currentUser!!.uid).child(student.studentId.toString())
            database.removeValue().addOnSuccessListener{
                Toast.makeText(context, "Student Deleted.", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(context, "Sorry", Toast.LENGTH_SHORT).show()
            }
        }
        holder.itemView.setOnClickListener{
            val intent = Intent(context, UpdateStudent::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("id", mList.get(position).studentId)
            intent.putExtra("name", mList.get(position).studentName)
            intent.putExtra("contact", mList.get(position).contactNumber)
            intent.putExtra("subject", mList.get(position).subjectName)
            intent.putExtra("salary", mList.get(position).salary)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.name)
        val subject : TextView = itemView.findViewById(R.id.subject)
        val contact : TextView = itemView.findViewById(R.id.contact)
        val salary : TextView = itemView.findViewById(R.id.salary)
        val delete : ImageButton = itemView.findViewById(R.id.deleteBtn)
    }
}