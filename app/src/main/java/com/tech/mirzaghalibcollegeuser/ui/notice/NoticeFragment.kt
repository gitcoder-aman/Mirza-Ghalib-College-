package com.tech.mirzaghalibcollegeuser.ui.notice

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.tech.mirzaghalibcollegeuser.adapter.NoticeAdapter
import com.tech.mirzaghalibcollegeuser.databinding.FragmentNoticeBinding
import com.tech.mirzaghalibcollegeuser.model.NoticeModel

class NoticeFragment : Fragment() {

    private lateinit var binding:FragmentNoticeBinding
    private var dataReference: DatabaseReference? = null
    private  var dialog: ProgressDialog ?= null
    private var adapter:NoticeAdapter ?= null
    private  var noticeList:ArrayList<NoticeModel> = ArrayList()
    private var oldMyNotice = arrayListOf<NoticeModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoticeBinding.inflate(layoutInflater, container, false)

        dataReference = FirebaseDatabase.getInstance().reference.child("Notice")

        dialog = ProgressDialog(activity)

        dialog?.setMessage("Loading...")
        dialog?.setCancelable(false)
        dialog?.show()

        binding.noticeRecycler.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        dataReference!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                noticeList.clear()
                if (snapshot.exists()) {
                    for (childSnapshot in snapshot.children) {

                        val noticeModel = childSnapshot.getValue(NoticeModel::class.java)
                        noticeList.add(noticeModel!!)
                    }

                    noticeList.sortWith(SortByDate())  //sort by date notice
                    adapter = NoticeAdapter(requireActivity(), noticeList)
                    oldMyNotice = noticeList as ArrayList<NoticeModel>  //just store data in oldMyNotice for searching image
                    binding.noticeRecycler.adapter = adapter
                    adapter!!.notifyDataSetChanged()
                    dialog?.dismiss()
                } else {
                    dialog?.dismiss()
                    Toast.makeText(activity, "No any data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                dialog?.dismiss()
                Toast.makeText(activity, "Cancelled", Toast.LENGTH_SHORT).show()
            }

        })

        binding.editSearch.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val searchText = p0.toString().trim() // Get the current search text

                // Call a method to perform the search operation with the current search text
                NoticeFiltering(searchText)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        return binding.root
    }

    private fun NoticeFiltering(p0:String?) {
        val newFilteredList = arrayListOf<NoticeModel>()
        for (i in oldMyNotice) {
            if (i.getTitle()?.contains(p0!!) == true) {
                newFilteredList.add(i)
            }
            adapter?.filteringForSearch(newFilteredList)
        }
    }
    class SortByDate : Comparator<NoticeModel> {
        override fun compare(o1: NoticeModel?, o2: NoticeModel?): Int {
            Log.d("@@@@",o1?.getTimestamp().toString())
            Log.d("@@@@",o2?.getTimestamp().toString())
            return ((o2?.getTimestamp())?.minus(o1?.getTimestamp()!!))?.toInt()!!
        }
    }
}