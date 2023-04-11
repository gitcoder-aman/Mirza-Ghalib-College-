package com.tech.mirzaghalibcollegeuser.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.tech.mirzaghalibcollegeuser.R
import com.tech.mirzaghalibcollegeuser.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  FragmentHomeBinding.inflate(layoutInflater, container, false)

        val imageList = ArrayList<SlideModel>() // Create image list


        imageList.add(SlideModel(R.drawable.college_image,ScaleTypes.FIT))
        imageList.add(SlideModel("https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/sliderImage%2F%5BB%408021816jpg?alt=media&token=faa370d8-77af-432e-9fc8-a2fcfb078006",ScaleTypes.FIT))
        imageList.add(SlideModel("https://firebasestorage.googleapis.com/v0/b/mirza-ghalib-college.appspot.com/o/sliderImage%2F%5BB%40cfeb92djpg?alt=media&token=09759766-71d1-484f-aa6f-6e2b9e37e821",ScaleTypes.FIT))
        imageList.add(SlideModel("https://bit.ly/3fLJf72",ScaleTypes.FIT))


        binding.imageSlider.setImageList(imageList)
        binding.imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                // You can listen here.
            }
            override fun doubleClick(position: Int) {
                // Do not use onItemSelected if you are using a double click listener at the same time.
                // Its just added for specific cases.
                // Listen for clicks under 250 milliseconds.
            } })

        binding.imageSlider.setSlideAnimation(AnimationTypes.ZOOM_OUT)
        binding.imageSlider.startSliding(3000) // with new period
//        binding.imageSlider.startSliding()
//        binding.imageSlider.stopSliding()
        return binding.root
    }

}