package com.tech.mirzaghalibcollegeuser.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tech.mirzaghalibcollegeuser.R
import com.tech.mirzaghalibcollegeuser.model.NoticeModel
import com.tech.mirzaghalibcollegeuser.model.TeacherModel


class TeacherListAdapter(
    private val context: Context,
    private var teacherList: ArrayList<TeacherModel>
) : RecyclerView.Adapter<TeacherListAdapter.ViewHolder>() {

    fun filteringForSearch(newFilteredList: ArrayList<TeacherModel>) {
        teacherList = newFilteredList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context)
                .inflate(R.layout.teacher_faculty_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return teacherList.size
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val teacherModel: TeacherModel = teacherList[position]

        holder.teacherName?.text = teacherModel.getTeacherName()
        holder.teacherPost?.text = teacherModel.getTeacherPost()

        Picasso.get().load(teacherModel.getTeacherImage())
            .placeholder(R.drawable.ic_no_image)
            .into(holder.circularImage)

        holder.itemView.setOnClickListener {

            if (teacherModel.getIsExpandable()) {
                holder.scrollView?.visibility = View.VISIBLE
                teacherModel.setIsExpandable(false)
            } else {
                holder.scrollView?.visibility = View.GONE
                teacherModel.setIsExpandable(true)
            }

            holder.teacherQualification?.setText(teacherModel.getTeacherQualification())
            holder.teacherSpecialize?.setText(teacherModel.getTeacherSpecialize())
            holder.teacherAddress?.setText(teacherModel.getTeacherAddress())
            holder.teacherMobile?.setText(teacherModel.getTeacherMobileNo())
            holder.teacherEmail?.setText(teacherModel.getTeacherEmail())
            holder.department?.setText(teacherModel.getDepartment())
            holder.teacherCV?.text = teacherModel.getTeacherCVPdfTitle()


            holder.teacherCV?.setOnClickListener {

                if (teacherModel.getTeacherCVPdf() != null) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setDataAndType( Uri.parse(teacherModel.getTeacherCVPdf()),"application/pdf")
                    intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "No CV Upload!", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var circularImage: ImageView? = null
        var teacherName: TextView? = null
        var teacherPost: TextView? = null

        //edittext
        var teacherQualification: EditText? = null
        var teacherSpecialize: EditText? = null
        var teacherAddress: EditText? = null
        var teacherMobile: EditText? = null
        var teacherEmail: EditText? = null
        var department: EditText? = null
        var teacherCV: TextView? = null
        var scrollView: ScrollView? = null

        init {
            circularImage = itemView.findViewById(R.id.circular_image)
            teacherName = itemView.findViewById(R.id.teacherName)
            teacherPost = itemView.findViewById(R.id.teacherPost)
            teacherQualification = itemView.findViewById(R.id.teacherQualification)
            teacherSpecialize = itemView.findViewById(R.id.teacherSpecialize)
            teacherAddress = itemView.findViewById(R.id.teacherAddress)
            teacherMobile = itemView.findViewById(R.id.teacherMobile)
            teacherEmail = itemView.findViewById(R.id.teacherEmail)
            department = itemView.findViewById(R.id.teacherDepartment)
            teacherCV = itemView.findViewById(R.id.CVpdfSelector)
            scrollView = itemView.findViewById(R.id.teacherScroll)
        }
    }
}