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
import com.tech.mirzaghalibcollegeuser.R
import com.tech.mirzaghalibcollegeuser.model.ImageSliderModel

class ActivityAdapter(
    private val context: Context,
    private var activityList: ArrayList<ImageSliderModel>?
) : RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.activity_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return activityList?.size!!
    }

    @SuppressLint("ResourceType", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val activityModel = activityList?.get(position)

        holder.activityTitle?.text = activityModel?.getCategory()

        Picasso.get().load(activityModel?.getImage())
            .placeholder(R.drawable.ic_no_image)
            .into(holder.thumbnail_image)

        holder.itemView.setOnClickListener {
            val intent: Intent = Intent(context, GalleryImageActivity::class.java)
            intent.putExtra("activityName",activityModel?.getCategory())
            Toast.makeText(context, activityModel?.getCategory(), Toast.LENGTH_SHORT).show()
            context.startActivity(intent)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var activityTitle: TextView? = null
        var thumbnail_image: ImageView? = null

        init {
            activityTitle = itemView.findViewById(R.id.activityTitle)
            thumbnail_image = itemView.findViewById(R.id.thumbnail_image)
        }
    }
}