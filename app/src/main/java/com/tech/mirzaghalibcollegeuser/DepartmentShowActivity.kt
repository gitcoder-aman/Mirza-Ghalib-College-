package com.tech.mirzaghalibcollegeuser

import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import com.tech.mirzaghalibcollegeuser.adapter.DepartmentAdapter
import com.tech.mirzaghalibcollegeuser.databinding.ActivityDepartmentShowBinding

class DepartmentShowActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDepartmentShowBinding
    private var dialog:ProgressDialog?= null
    private var dataReference: DatabaseReference?= null
    private var departmentList:ArrayList<String> = ArrayList()
    private var adapter: DepartmentAdapter?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDepartmentShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Departments"

        dataReference = FirebaseDatabase.getInstance().reference.child("teachers")
        dialog = ProgressDialog(this)
        dialog!!.setMessage("Loading...")
        dialog!!.setCancelable(false)

        dataFetchOfChildOfTeacher()
        binding.departmentRecycler.layoutManager = GridLayoutManager(this,2)
        adapter = DepartmentAdapter(this,departmentList)
        binding.departmentRecycler.adapter = adapter

    }

    private fun dataFetchOfChildOfTeacher() {
        dialog!!.show()
        dataReference!!.addListenerForSingleValueEvent(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {

                departmentList.clear()
                if(snapshot.exists()){
                    for(childSnapshot in snapshot.children){
                        val department = childSnapshot.key

                        departmentList.add(department!!)
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
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == android.R.id.home) { // Check if the clicked item is the toolbar back button
            onBackPressed() // Call the onBackPressed() method to handle the back button click
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}