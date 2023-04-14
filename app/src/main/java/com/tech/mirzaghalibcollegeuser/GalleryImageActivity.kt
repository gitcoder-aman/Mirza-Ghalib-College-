package com.tech.mirzaghalibcollegeuser

import android.app.ProgressDialog
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import com.tech.mirzaghalibcollegeuser.adapter.GalleryAdapter
import com.tech.mirzaghalibcollegeuser.databinding.ActivityGalleryImageBinding
import com.tech.mirzaghalibcollegeuser.model.ImageSliderModel

class GalleryImageActivity : AppCompatActivity() {

    private lateinit var binding:ActivityGalleryImageBinding
    private lateinit var adapter:GalleryAdapter
    private var galleryList:ArrayList<ImageSliderModel> = ArrayList()
    private var dataReference: DatabaseReference? = null
    private var activityName:String ?= null
    private lateinit var dialog:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        dataReference = FirebaseDatabase.getInstance().reference.child("gallery")
        dialog = ProgressDialog(this)
        dialog.setTitle("Please wait")
        dialog.setMessage("Loading...")
        dialog.setCancelable(false)
        
        activityName = intent.getStringExtra("activityName")
        supportActionBar?.title = activityName

        dialog.show()
        fetchCategoryImage()

        binding.galleryRecycler.layoutManager = GridLayoutManager(this,4)

    }

    private fun fetchCategoryImage() {

        dataReference?.child(activityName.toString())?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                galleryList.clear()
                if(snapshot.exists()){
                    for (child in snapshot.children){
                        val galleryModel = child.getValue(ImageSliderModel::class.java)
                        galleryList.add(galleryModel!!)
                    }
                    adapter = GalleryAdapter(applicationContext, galleryList)
                    binding.galleryRecycler.adapter = adapter
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }else{
                    dialog.dismiss()
                    Toast.makeText(applicationContext, "No any Images", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                dialog.dismiss()
                Toast.makeText(applicationContext, "Something went wrong.", Toast.LENGTH_SHORT).show()
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