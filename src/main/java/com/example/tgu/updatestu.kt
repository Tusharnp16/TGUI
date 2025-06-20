package com.example.tgu


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.tgu.databinding.ActivityShowuserBinding
import com.example.tgu.databinding.ActivityUpdatestuBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private lateinit var database: DatabaseReference
private lateinit var binding : ActivityUpdatestuBinding
private lateinit var radioButton: RadioButton

class
updatestu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatestuBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fname3 = intent.getStringExtra("fname2")
        val lname3 = intent.getStringExtra("lname2")
        val course3 = intent.getStringExtra("course2")
        val mobile3 = intent.getStringExtra("mobile2")
        val gender3 = intent.getStringExtra("gender2")

        binding.txtfname.setText(fname3)
        binding.txtlname.setText(lname3)
        binding.txtcourse.setText(course3)
        binding.txtmobile.setText(mobile3)

        if (gender3 == "Male") {
            binding.rggender.check(binding.rbmale.id)
        } else {
            binding.rggender.check(binding.rbfemale.id)
        }



        binding.updbtn.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {


                    if (binding.txtmobile.text.toString().length == 10) {
                        val firstname = binding.txtfname.text.toString()
                        val lastname = binding.txtlname.text.toString()
                        val mobile = binding.txtmobile.text.toString()
                        val course = binding.txtcourse.text.toString()
                        //  val email=binding.txtemail.text.toString()

                        val selectedid = binding.rggender.checkedRadioButtonId
                        radioButton = findViewById(selectedid)
                        val gender1 = radioButton.text.toString()
                        updateuser(firstname, lastname, mobile, course, gender1)
                    } else {
                        binding.txtmobile.setError("Number Must be 10")
                    }

                }
            })

    }

    private fun updateuser(firstname : String,lastname: String,mobile1: String,course: String,gender1: String) {

        database=FirebaseDatabase.getInstance().getReference("students")
        val student= mapOf<String,String>(
            "firstname" to firstname,
            "lastname1" to lastname,
            "gender1" to gender1,
            "course1" to course
        )
        database.child(mobile1).updateChildren(student).addOnSuccessListener {
            binding.txtfname.text.clear()
            binding.txtlname.text.clear()
            binding.txtcourse.text.clear()
            binding.txtmobile.text.clear()
            binding.rggender.clearCheck()
            Toast.makeText(this@updatestu,"Data Updated Successfully",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this@updatestu,"Failed to Update",Toast.LENGTH_SHORT).show()
        }
    }
}