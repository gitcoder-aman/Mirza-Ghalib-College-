package com.tech.mirzaghalibcollegeuser.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tech.mirzaghalibcollegeuser.R
import com.tech.mirzaghalibcollegeuser.model.NoticeModel
import com.tech.mirzaghalibcollegeuser.utils.util

class NoticeAdapter(
    private val context: Context,
    private var noticeList: ArrayList<NoticeModel>
) : RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {

    fun filteringForSearch(newFilteredList: ArrayList<NoticeModel>) {
        noticeList = newFilteredList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.notice_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noticeList.size
    }

    @SuppressLint("ResourceType", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticeModel = noticeList[position]

        holder.noticeTitle?.text = noticeModel.getTitle()
        holder.noticeDateTime?.text = util.setTimeStamp(noticeModel.getTimestamp()!!)

        Picasso.get().load(noticeModel.getImage())
            .placeholder(R.drawable.ic_no_image)
            .into(holder.noticeImage)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var noticeTitle: TextView? = null
        var noticeDateTime: TextView? = null
        var noticeImage: ImageView? = null

        init {
            noticeTitle = itemView.findViewById(R.id.noticeTitle)
            noticeDateTime = itemView.findViewById(R.id.noticeDateTime)
            noticeImage = itemView.findViewById(R.id.imageView)
        }
    }
}