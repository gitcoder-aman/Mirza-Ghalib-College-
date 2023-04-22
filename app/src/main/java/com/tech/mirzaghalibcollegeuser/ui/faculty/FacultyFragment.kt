package com.tech.mirzaghalibcollegeuser.ui.faculty

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tech.mirzaghalibcollegeuser.adapter.TeacherListAdapter
import com.tech.mirzaghalibcollegeuser.databinding.FragmentFacultyBinding
import com.tech.mirzaghalibcollegeuser.model.TeacherModel


class FacultyFragment : Fragment() {

    private lateinit var binding:FragmentFacultyBinding
    private var dataReference: DatabaseReference? = null
    private  var dialog: ProgressDialog?= null
    private var adapter: TeacherListAdapter?= null
    private  var facultyList:ArrayList<TeacherModel> = ArrayList()
    private var oldFaculty = arrayListOf<TeacherModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  FragmentFacultyBinding.inflate(layoutInflater, container, false)

        dataReference = FirebaseDatabase.getInstance().reference.child("teachers")
        dialog = ProgressDialog(activity)
        dialog!!.setMessage("Loading Data...")
        dialog!!.setCancelable(false)
        dialog!!.show()

        binding.facultyRecycler.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)

        dataReference!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(childSnapshot in snapshot.children){
                        for(grandChild in childSnapshot.children) {

                        val teacherModel =grandChild.getValue(TeacherModel::class.java)

                        facultyList.add(teacherModel!!)
                        }
                    }
                    adapter = TeacherListAdapter(requireActivity(), facultyList)
                    oldFaculty = facultyList as ArrayList<TeacherModel>  //just store data in oldMyNotice for searching image
                    binding.facultyRecycler.adapter = adapter
                    adapter!!.notifyDataSetChanged()
                    dialog?.dismiss()
                }else{
                    dialog!!.dismiss()
                    Toast.makeText(activity, "No any data..", Toast.LENGTH_SHORT).show()
                    Log.d("@@@@","Snapshot is not exist")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "something went wrong.", Toast.LENGTH_SHORT).show()
                Log.d("@@@@","onCancelled"+error.message)
            }

        })

        binding.editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val searchText = p0.toString().trim() // Get the current search text

                // Call a method to perform the search operation with the current search text
                FacultyFiltering(searchText)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        return binding.root
    }

    private fun FacultyFiltering(p0:String?) {
        val newFilteredList = arrayListOf<TeacherModel>()
        for (i in oldFaculty) {
            if (i.getTeacherName()?.contains(p0!!) == true) {
                newFilteredList.add(i)
            }
            adapter?.filteringForSearch(newFilteredList)
        }
    }

}