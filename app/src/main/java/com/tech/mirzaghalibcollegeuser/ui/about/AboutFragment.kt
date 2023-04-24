package com.tech.mirzaghalibcollegeuser.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tech.mirzaghalibcollegeuser.R
import com.tech.mirzaghalibcollegeuser.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private lateinit var binding:FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

}