package com.example.tgu

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text
private lateinit var database: DatabaseReference


public class studentadapter(private val studentlist : ArrayList<recycleuserdata>) : RecyclerView.Adapter<studentadapter.MyViewHolder>() {

    lateinit var context: Context
    lateinit var arrayList: ArrayList<recycleuserdata>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.studentitemlist, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return studentlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = studentlist[position]
        holder.firstname.text = currentitem.firstname
        holder.mobile.text = currentitem.mobile1
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstname: TextView = itemView.findViewById(R.id.namerecy)
        val mobile: TextView = itemView.findViewById(R.id.mobilerecy)

        val btn: Unit = itemView.findViewById<Button>(R.id.delebtn).setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {

                    var mobile1: String = mobile.text.toString()
                    var context:Context
                    context=itemView.context
                    deletestudents(mobile1,context)
                }
            })
    }
}

private fun deletestudents(mobile: String,context: Context) {

    database = FirebaseDatabase.getInstance().getReference("students")
    database.child(mobile).removeValue().addOnSuccessListener {
        Toast.makeText(context," Data Deleted..Need to restart this activity ",Toast. LENGTH_SHORT);

    }.addOnFailureListener {
        Toast.makeText(context," Error occured during deleting student data ",Toast. LENGTH_SHORT);
    }
}
