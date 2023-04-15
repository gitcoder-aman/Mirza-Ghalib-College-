package com.tech.mirzaghalibcollegeuser.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tech.mirzaghalibcollegeuser.R
import com.tech.mirzaghalibcollegeuser.TeacherListActivity
import com.tech.mirzaghalibcollegeuser.utils.util.Companion.ImageSet

class DepartmentAdapter(
    private val context: Context,
    private val departmentList: ArrayList<String>
) : RecyclerView.Adapter<DepartmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.department_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return departmentList.size
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val departmentItem = departmentList[position]

        holder.departmentName?.text = departmentItem

        ImageSet(holder, departmentItem)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, TeacherListActivity::class.java)
            intent.putExtra("departName",departmentItem)
            Log.d("@@@@",departmentItem)
            context.startActivity(intent)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var departmentLogo: ImageView? = null
        var departmentName: TextView? = null

        init {
            departmentLogo = itemView.findViewById(R.id.departmentLogo)
            departmentName = itemView.findViewById(R.id.departmentName)
        }
    }
}