package com.tech.mirzaghalibcollegeuser

import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.tech.mirzaghalibcollegeuser.adapter.TeacherListAdapter
import com.tech.mirzaghalibcollegeuser.databinding.ActivityListTeacherBinding
import com.tech.mirzaghalibcollegeuser.model.TeacherModel

class TeacherListActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityListTeacherBinding
    private var dataReference: DatabaseReference?= null
    private var teacherList:ArrayList<TeacherModel> = ArrayList()
    private var adapter: TeacherListAdapter?=null
    private var dialog: ProgressDialog?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListTeacherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Faculty"

        dataReference = FirebaseDatabase.getInstance().reference.child("teachers")
        dialog = ProgressDialog(this)
        dialog!!.setMessage("Loading Data...")
        dialog!!.setCancelable(false)
        dialog!!.show()

        val intent = intent.getStringExtra("departName")
        if(intent != null){
            dataReference!!.child(intent).addListenerForSingleValueEvent(object : ValueEventListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {

                    if(snapshot.exists()){
                        for(childSnapshot in snapshot.children){
                            val teacherModel =childSnapshot.getValue(TeacherModel::class.java)
                            Log.d("@@@@", teacherModel?.getTeacherName()!!)

                            teacherList.add(teacherModel)
                        }
                        adapter?.notifyDataSetChanged()
                        dialog!!.dismiss()
                    }else{
                        Log.d("@@@@","snapshot is not exists")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("@@@@","OnCancelled")
                }

            })
        }else{
            Toast.makeText(this, "something went wrong.", Toast.LENGTH_SHORT).show()
        }
        binding.updateRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        adapter = TeacherListAdapter(this,teacherList)
        binding.updateRecycler.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == android.R.id.home) { // Check if the clicked item is the toolbar back button
            onBackPressed() // Call the onBackPressed() method to handle the back button click
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}