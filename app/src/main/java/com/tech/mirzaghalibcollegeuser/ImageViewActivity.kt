package com.tech.mirzaghalibcollegeuser

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.tech.mirzaghalibcollegeuser.databinding.ActivityImageViewBinding

class ImageViewActivity : AppCompatActivity() {

    private lateinit var binding:ActivityImageViewBinding
    private lateinit var dialog:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        dialog = ProgressDialog(this)
        dialog.setMessage("Loading...")
        dialog.setCancelable(false)
        dialog.show()

        val imageUrl = intent.getStringExtra("imageUrl")
        val activityName = intent.getStringExtra("activityName")
        supportActionBar?.title = activityName

        if(imageUrl != null){
            Picasso.get().load(imageUrl)
                .placeholder(R.drawable.ic_no_image)
                .into(binding.imageView)
        }else{
            Toast.makeText(this, "No Image!", Toast.LENGTH_SHORT).show()
        }
        dialog.dismiss()
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