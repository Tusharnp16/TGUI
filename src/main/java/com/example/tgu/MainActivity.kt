package com.example.tgu

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.tgu.databinding.ActivityMainBinding
import com.example.tgu.databinding.ActivityRegistractionactBinding

private lateinit var binding : ActivityMainBinding
public lateinit var adpwd : EditText
public lateinit var adlogbtn:Button
public lateinit var cancel:Button


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dialogbox = Dialog(this)
        dialogbox.setContentView(R.layout.custom_dialogbox)
        dialogbox.setCancelable(false)
        dialogbox.setTitle("login")
        adlogbtn = dialogbox.findViewById(R.id.adlogin)
        adpwd = dialogbox.findViewById(R.id.skey)
        cancel = dialogbox.findViewById(R.id.cancel)

        binding.stubtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MainActivity, studentact::class.java);
                startActivity(intent);
            }
        });

        binding.admbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dialogbox.show()
            }
        })
        adlogbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if(adpwd.text.toString()=="9876"){
                    val intent = Intent(this@MainActivity, recycleuserlist::class.java);
                    startActivity(intent);
                    adpwd.text.clear()
                }
                else{
                    Toast.makeText(this@MainActivity, "Incorrect Security Key", Toast.LENGTH_SHORT).show()
                    adpwd.text.clear()
                }
            }
        });
        cancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dialogbox.dismiss()
                adpwd.text.clear()
            }
        });
    }
}