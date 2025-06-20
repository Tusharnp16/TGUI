package com.example.tgu

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.airbnb.lottie.LottieAnimationView

class custome_progrgess(val mactivity:Activity) {
    private lateinit var isdialog:AlertDialog
    val anim=LottieAnimationView(mactivity)

        fun start(){
            val inflater=mactivity.layoutInflater
            val dialogView=inflater.inflate(R.layout.activity_custome_progrgess,null)
            val builder=AlertDialog.Builder(mactivity)
            builder.setView(dialogView)
            builder.setCancelable(false)
            isdialog=builder.create()
            isdialog.show()
            anim.setAnimation("prg.json")
            anim.playAnimation()
        }
    fun done(){
        isdialog.dismiss()
    }
    }
