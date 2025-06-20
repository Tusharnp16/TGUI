package com.example.tgu

import android.R.layout.*
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.tgu.databinding.ActivityRegistractionactBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


private lateinit var binding : ActivityRegistractionactBinding
private lateinit var radioButton: RadioButton
private lateinit var database :DatabaseReference


class registractionact : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistractionactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var prg=custome_progrgess(this)


      binding.regbtn.setOnClickListener(object :View.OnClickListener{
          override fun onClick(p0: View?) {
                if(validation()){
                    userdataintodatabase()
                 //   prg.done()
                     // val intent = Intent(this@registractionact, showuser::class.java)*/
                      //startActivity(intent)
                  }
                  else{
                      Toast.makeText(this@registractionact,"Please fill All The fields",Toast.LENGTH_SHORT).show()
                  }
          }
      })
   }

    private fun userdataintodatabase() {
        var prg=custome_progrgess(this@registractionact)
        prg.start()
        val firstname= binding.txtfname.text.toString()
        val lastname1= binding.txtlname.text.toString()
        val emailid1= binding.txtemail.text.toString()
        val course1= binding.txtcourse.text.toString()
        val mobile1= binding.txtmobile.text.toString()
        val selectedid=binding.rggender.checkedRadioButtonId
        radioButton=findViewById(selectedid)
        val gender1=radioButton.text.toString()

        database=FirebaseDatabase.getInstance().getReference("students")

        val student=user(firstname, lastname1, emailid1, gender1, course1, mobile1)
        database.child(mobile1).setValue(student).addOnSuccessListener {
            binding.txtfname.text.clear()
            binding.txtlname.text.clear()
            binding.txtemail.text.clear()
            binding.txtcourse.text.clear()
            binding.txtmobile.text.clear()
            binding.rggender.clearCheck()
            prg.done()

            Toast.makeText(this@registractionact,"Admission has sucessfully taken in TGU",Toast.LENGTH_LONG).show()

        }.addOnFailureListener {
            Toast.makeText(this@registractionact,"Something Went Wrong Try Again.....",Toast.LENGTH_LONG).show()
        }
        }

    private fun validation():Boolean {
        val emailPattern : String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        if(binding.txtfname.getText().toString().length==0 && binding.txtlname.getText().toString().length==0&& binding.txtcourse.getText().toString().length==0){
            binding.txtfname.setError("Must be")
            binding.txtlname.setError("Must be")
            binding.txtcourse.setError("Must be")
            return false
        }
        if( binding.txtmobile.length()!=10 ){
            binding.txtmobile.setError("Length Must be 10")
        return false
    }
        if(binding.txtemail.length() == 0) {
        binding.txtemail.setError("Please enter email-id")
        return false
    } else if (!binding.txtemail.getText().toString().matches(emailPattern.toRegex())) {
            binding.txtemail.setError("Invalid email address")
        return false
    }
        return true
    }
}