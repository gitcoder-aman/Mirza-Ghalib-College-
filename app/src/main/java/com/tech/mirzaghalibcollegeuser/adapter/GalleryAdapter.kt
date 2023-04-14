package com.tech.mirzaghalibcollegeuser.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tech.mirzaghalibcollegeuser.GalleryImageActivity
import com.tech.mirzaghalibcollegeuser.ImageViewActivity
import com.tech.mirzaghalibcollegeuser.R
import com.tech.mirzaghalibcollegeuser.model.ImageSliderModel

class GalleryAdapter(
    private val context: Context,
    private var galleryList: ArrayList<ImageSliderModel>?
) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.gallery_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return galleryList?.size!!
    }

    @SuppressLint("ResourceType", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val galleryModel = galleryList?.get(position)

        Picasso.get().load(galleryModel?.getImage())
            .placeholder(R.drawable.ic_no_image)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(context,ImageViewActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("imageUrl",galleryModel?.getImage())
            intent.putExtra("activityName",galleryModel?.getCategory())
            context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView? = null

        init {
            image = itemView.findViewById(R.id.imageView)
        }
    }
}