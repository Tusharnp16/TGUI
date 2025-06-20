package com.example.tgu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.tgu.databinding.ActivityRegistractionactBinding
import com.example.tgu.databinding.ActivityShowuserBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private lateinit var binding : ActivityShowuserBinding
private lateinit var intent: Intent
private lateinit var database: DatabaseReference

class showuser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityShowuserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mobile =intent.getStringExtra("number")
        if (mobile != null) {
            showuserdata(mobile)
        }
        else{
            Toast.makeText(this@showuser,"Invalid",Toast.LENGTH_SHORT).show()
        }

        binding.updbtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@showuser, updatestu::class.java)
                val fname= binding.rname.text.toString()
                val lname= binding.rlname.text.toString()
                val course= binding.rcourse.text.toString()
                val mobile= binding.rmobile.text.toString()
                val gender= binding.rgen.text.toString()
                val email= binding.remail.text.toString()
                intent.putExtra("fname2",fname)
                intent.putExtra("lname2",lname)
                intent.putExtra("course2",course)
                intent.putExtra("mobile2",mobile)
                intent.putExtra("email2",email)
                intent.putExtra("gender2",gender)
                startActivity(intent)
            }
    })
}

    private fun showuserdata(mobile : String) {
        database=FirebaseDatabase.getInstance().getReference("students")
        database.child(mobile).get().addOnSuccessListener {
            if(it.exists()){

                val firstname=it.child("firstname").value
                val lastname=it.child("lastname1").value
                val emailid=it.child("emailid1").value
                val mobilenumber=it.child("mobile1").value
                val course=it.child("course1").value
                val gender=it.child("gender1").value

                binding.rname.text=firstname.toString()
                binding.rlname.text=lastname.toString()
                binding.remail.text=emailid.toString()
                binding.rgen.text=gender.toString()
                binding.rcourse.text=course.toString()
                binding.rmobile.text=mobilenumber.toString()
            }else{
                Toast.makeText(this@showuser,"Student doesn't Exists",Toast.LENGTH_SHORT).show()
            }
        }
    }
}