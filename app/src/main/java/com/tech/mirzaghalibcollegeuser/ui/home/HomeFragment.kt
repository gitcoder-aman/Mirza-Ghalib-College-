package com.tech.mirzaghalibcollegeuser.ui.home

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.database.*
import com.tech.mirzaghalibcollegeuser.DepartmentShowActivity
import com.tech.mirzaghalibcollegeuser.databinding.FragmentHomeBinding
import com.tech.mirzaghalibcollegeuser.model.ImageSliderModel
import com.tech.mirzaghalibcollegeuser.ui.notice.NoticeFragment
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var dataReference: DatabaseReference? = null
    private var imageSliderList: ArrayList<ImageSliderModel> = ArrayList()
    private  var dialog: ProgressDialog ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        dataReference = FirebaseDatabase.getInstance().reference

        dialog = ProgressDialog(activity)

        dialog?.setMessage("Loading...")
        dialog?.setCancelable(false)
        dialog?.show()

        fetchSliderImageData("sliderImage")
        fetchSliderImageData("VipVisitImage")

        binding.cardNotice.setOnClickListener{

            //change to fragment in navigation
            val navController = findNavController()
            navController.popBackStack(navController.graph.startDestinationId, true)
            navController.navigate(com.tech.mirzaghalibcollegeuser.R.id.navigation_notice)
        }
        binding.cardActivity.setOnClickListener {
            findNavController().navigate(com.tech.mirzaghalibcollegeuser.R.id.navigation_activity)
        }
        binding.cardDepartment.setOnClickListener {
            startActivity(Intent(activity, DepartmentShowActivity::class.java))
        }

        return binding.root
    }

    private fun fetchSliderImageData(path:String) {
        //fetching all the slider image data
        dataReference?.child(path)?.addListenerForSingleValueEvent(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {

                imageSliderList.clear()
                if (snapshot.exists()) {
                    for (childSnapshot in snapshot.children) {

                        val sliderList = childSnapshot.getValue(ImageSliderModel::class.java)
                        imageSliderList.add(sliderList!!)
                    }
                        setSliderImage(path)

                } else {
                    dialog!!.dismiss()
                    Toast.makeText(activity, "No any Data!", Toast.LENGTH_SHORT).show()
                    Log.d("@@@@", "snapshot is not exists")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("@@@@", "OnCancelled")
            }
        })
    }

    private fun setSliderImage(path:String) {
        imageSliderList.sortWith(SortByDate())  //sort slider image in date wise

        val imageList = ArrayList<SlideModel>() // Create image list

        for (i in imageSliderList.indices) {
            imageList.add(
                SlideModel(
                    imageSliderList[i].getImage(),
                    imageSliderList[i].getTitle(), ScaleTypes.FIT
                )
            )
        }

        if(path == "sliderImage"){

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
            dialog?.dismiss()

        }else if(path == "VipVisitImage"){ //this is also slider image view

            binding.vipSlider.setImageList(imageList)
            binding.vipSlider.setItemClickListener(object : ItemClickListener {
                override fun onItemSelected(position: Int) {
                    Toast.makeText(activity, "on item clicked", Toast.LENGTH_SHORT)
                        .show()
                    // You can listen here.
                }

                override fun doubleClick(position: Int) {
                    Toast.makeText(activity, "double click", Toast.LENGTH_SHORT).show()
                }
            })

            binding.vipSlider.setSlideAnimation(AnimationTypes.ZOOM_OUT)
            binding.vipSlider.startSliding(3000)
            dialog?.dismiss()
        }

    }

    class SortByDate : Comparator<ImageSliderModel> {
        override fun compare(o1: ImageSliderModel?, o2: ImageSliderModel?): Int {
            return ((o2?.getTimeStamp())?.minus(o1?.getTimeStamp()!!))?.toInt()!!
        }
    }
}