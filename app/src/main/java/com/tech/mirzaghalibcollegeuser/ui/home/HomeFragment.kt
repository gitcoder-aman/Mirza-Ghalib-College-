package com.tech.mirzaghalibcollegeuser.ui.home

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.tech.mirzaghalibcollegeuser.databinding.FragmentHomeBinding
import com.tech.mirzaghalibcollegeuser.model.ImageSliderModel
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var dataReference: DatabaseReference? = null
    private var storageReference: StorageReference? = null
    private var imageSliderList: ArrayList<ImageSliderModel> = ArrayList()
    private  var dialog: ProgressDialog ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        dataReference = FirebaseDatabase.getInstance().reference.child("sliderImage")
        storageReference = FirebaseStorage.getInstance().reference.child("sliderImage")

        dialog = ProgressDialog(activity)

        dialog?.setMessage("Loading...")
        dialog?.setCancelable(false)
        dialog?.show()

        //fetching all the slider image data
        dataReference!!.addListenerForSingleValueEvent(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {

                imageSliderList.clear()
                if (snapshot.exists()) {
                    for (childSnapshot in snapshot.children) {

                        val sliderList = childSnapshot.getValue(ImageSliderModel::class.java)
                        imageSliderList.add(sliderList!!)
                    }
                    setSliderImage()
                } else {
                    Log.d("@@@@", "snapshot is not exists")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("@@@@", "OnCancelled")
            }
        })
        Log.d("@@@@", "Size " + (imageSliderList.size))

        return binding.root
    }

    private fun setSliderImage() {
        imageSliderList.sortWith(SortByDate())

        val imageList = ArrayList<SlideModel>() // Create image list

        for (i in imageSliderList.indices) {
            imageList.add(
                SlideModel(
                    imageSliderList[i].getImage(),
                    imageSliderList[i].getTitle(), ScaleTypes.FIT
                )
            )
        }
        dialog?.dismiss()

        binding.imageSlider.setImageList(imageList)
        binding.imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                Toast.makeText(activity, "on item clicked", Toast.LENGTH_SHORT)
                    .show()
                // You can listen here.
            }

            override fun doubleClick(position: Int) {
                Toast.makeText(activity, "double click", Toast.LENGTH_SHORT).show()
            }
        })

        binding.imageSlider.setSlideAnimation(AnimationTypes.ZOOM_OUT)
        binding.imageSlider.startSliding(3000) // with new period
    }

    class SortByDate : Comparator<ImageSliderModel> {
        override fun compare(o1: ImageSliderModel?, o2: ImageSliderModel?): Int {
            return ((o2?.getTimeStamp())?.minus(o1?.getTimeStamp()!!))?.toInt()!!
        }
    }

    private fun fetchSliderImageData(): ArrayList<ImageSliderModel>? {

        val sliderList: ArrayList<ImageSliderModel> = ArrayList()

        dataReference!!.addListenerForSingleValueEvent(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {

                sliderList.clear()
                if (snapshot.exists()) {
                    for (childSnapshot in snapshot.children) {
                        Log.d("@@@@", snapshot.toString())
                        val imageList = childSnapshot.getValue(ImageSliderModel::class.java)
                        sliderList.add(imageList!!)
                        Log.d("@@@@", "Size " + (sliderList.size))
                    }
//                    dialog.dismiss()
                } else {
                    Log.d("@@@@", "snapshot is not exists")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("@@@@", "OnCancelled")
            }
        })
        return sliderList
    }

}