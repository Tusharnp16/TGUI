package com.example.tgu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.tgu.databinding.ActivityGetotpactBinding
import com.example.tgu.databinding.ActivityRegistractionactBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private lateinit var binding : ActivityGetotpactBinding
private lateinit var database:DatabaseReference

class getotpact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetotpactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mobile1: String? =intent.getStringExtra("number")

        binding.showmobile.setText(String.format("+91 %s",intent.getStringExtra("number")))

        binding.verotp.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    if(binding.otp1.length()==1&&binding.otp2.length()==1&&binding.otp3.length()==1&&binding.otp4.length()==1) {
                        Toast.makeText(this@getotpact, "Entered Otp doesn't match", Toast.LENGTH_SHORT).show()
                        if (mobile1 != null) {
                            nextact2(mobile1)
                        }
                    }
                    else{
                        Toast.makeText(this@getotpact, "Please enter all the fields", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        movetonext()
    }

    private fun movetonext() {
        binding.otp1.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(!s.toString().trim().isEmpty()){
                    binding.otp2.requestFocus();
                }
            }
        })
        binding.otp2.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(!s.toString().trim().isEmpty()){
                    binding.otp3.requestFocus();
                }
            }
        })
        binding.otp3.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(!s.toString().trim().isEmpty()){
                    binding.otp4.requestFocus();
                }
            }
        })

    }
    private fun nextact(mobile2: String) {
        val prg=custome_progrgess(this@getotpact)
        prg.start()
        database = FirebaseDatabase.getInstance().getReference("students")
        database.child(mobile2).get().addOnSuccessListener {
            if (it.exists()) {
                val intent = Intent(this@getotpact, showuser::class.java);
                intent.putExtra("number", mobile2)
                startActivity(intent);
                prg.done()
            } else {
                prg.done()
                Toast.makeText(this@getotpact, "Invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun nextact2(mobile2: String) {
        val prg=custome_progrgess(this@getotpact)
        prg.start()
                val intent = Intent(this@getotpact, showuser::class.java);
                intent.putExtra("number", mobile2)
                startActivity(intent);
                prg.done()

        }
    }
