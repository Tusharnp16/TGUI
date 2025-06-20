package com.example.tgu

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

private lateinit var database: DatabaseReference
private lateinit var recy: RecyclerView
private lateinit var arraylist: ArrayList<recycleuserdata>
private lateinit var deletebtn:Button
private lateinit var mobile: TextView

class recycleuserlist : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycleuserlist)
        var recyclerView: RecyclerView
        recy = findViewById(R.id.recycleview)
      //  var inflate : LayoutInflater=this.layoutInflater
     //   val itemView= LayoutInflater.from(parent).inflate(R.layout.studentitemlist,parent,false)
        //   deletebtn = findViewById(R.id.delebtn)
//        mobile=findViewById(R.id.mobilerecy)
        recy.layoutManager = LinearLayoutManager(this)
        recy.setHasFixedSize(true)
        arraylist = arrayListOf<recycleuserdata>()
        getstudentdata()

        /*       deletebtn.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    var mobile1 : String= mobile.text.toString()
                    deletestudents(mobile1)
                }
            })
    }*/

/*    private fun deletestudents(mobile: String) {
        database=FirebaseDatabase.getInstance().getReference("students")
        database.child(mobile).removeValue().addOnSuccessListener {
            Toast.makeText(this,"Student Data Deleted",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Some error occure during deleting data",Toast.LENGTH_SHORT).show()
        }
    }*/
    }
    private fun getstudentdata() {

            database = FirebaseDatabase.getInstance().getReference("students")
            database.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (studentSnapshot in snapshot.children) {
                            val student = studentSnapshot.getValue(recycleuserdata::class.java)
                            arraylist.add(student!!)
                        }
                        recy.adapter = studentadapter(arraylist)

                    }

                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@recycleuserlist, "$error ", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

