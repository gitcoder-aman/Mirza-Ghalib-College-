package com.tech.mirzaghalibcollegeuser.ui.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.tech.mirzaghalibcollegeuser.adapter.ActivityAdapter
import com.tech.mirzaghalibcollegeuser.databinding.FragmentActivityBinding
import com.tech.mirzaghalibcollegeuser.model.ImageSliderModel

class ActivityFragment : Fragment() {

    private lateinit var binding:FragmentActivityBinding
    private var dataReference: DatabaseReference? = null
    private var activityList:ArrayList<ImageSliderModel> ?= ArrayList()
    private var adapter:ActivityAdapter ?=null
    private  var dialog: ProgressDialog?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentActivityBinding.inflate(layoutInflater, container, false)

        dataReference = FirebaseDatabase.getInstance().reference.child("gallery")
        binding.activityRecycler.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        dialog = ProgressDialog(activity)
        dialog!!.setMessage("Loading...")
        dialog!!.setCancelable(false)
        dialog!!.show()

        dataReference?.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()) {
                    for (child in snapshot.children) {

                        for (grandChild in child.children) {

                            val activityModel = grandChild.getValue(ImageSliderModel::class.java)

                            activityList?.add(activityModel!!)
                            break
                        }

                    }
                }else{
                    dialog!!.dismiss()
                    Toast.makeText(activity, "No Any Activity", Toast.LENGTH_SHORT).show()
                }

                adapter = ActivityAdapter(requireActivity(),activityList)
                binding.activityRecycler.adapter = adapter
                adapter!!.notifyDataSetChanged()
                dialog!!.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                dialog!!.dismiss()
                Toast.makeText(activity, "Something went wrong.", Toast.LENGTH_SHORT).show()
                Log.d("@@@@g","OnCancelled")
            }

        })
        return binding.root
    }

}