package com.example.tgu

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private lateinit var newregisteraction : TextView
private lateinit var loginbtn:Button
private lateinit var tempotp:Button
private lateinit var getmobile: EditText
private lateinit var database: DatabaseReference

class studentact : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studentact)
        newregisteraction=findViewById(R.id.register)
        loginbtn=findViewById(R.id.loginbtn);
        tempotp=findViewById(R.id.tempotp);
        getmobile=findViewById(R.id.mobilenumber);
        val mobile1= getmobile.text.toString()

        newregisteraction.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val intent = Intent(this@studentact, registractionact::class.java);
                    startActivity(intent);
                }
                })
        tempotp.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    if(validation()) {
                        val intent = Intent(this@studentact, getotpact::class.java);
                        val value = getmobile.getText().toString();
                        intent.putExtra("number", value)
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(this@studentact, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show()
                    }
                }
            })

        loginbtn.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    if(validation()) {
                        nextact(mobile1)
                    }
                    else{
                            Toast.makeText(this@studentact, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show()
                        }
                }
            })
    }

    private fun nextact(mobile2: String) {
        val prg=custome_progrgess(this@studentact)
        prg.start()
        database = FirebaseDatabase.getInstance().getReference("students")
        database.child(mobile2).get().addOnSuccessListener {
            if (it.exists()) {
                val intent = Intent(this@studentact, showuser::class.java);
                val value = getmobile.getText().toString();
                intent.putExtra("number", value)
                startActivity(intent);
                prg.done()
            } else {
                prg.done()
                Toast.makeText(this@studentact, "Invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validation(): Boolean {
        if(getmobile.length()!=10 ){
            getmobile.setError("Length Must be 10")
            return false
        }
        return true
    }
}