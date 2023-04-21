package com.tech.mirzaghalibcollegeuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.tech.mirzaghalibcollegeuser.databinding.ActivityFeedbackBinding

class FeedbackActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFeedbackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Feedback"

        binding.studentFeedbackBtn.setOnClickListener {
            val intent = Intent(this,WebViewActivity::class.java)
            intent.putExtra("link",resources.getString(R.string.student_feedback_link))
            startActivity(intent)
        }

        binding.teacherFeedbackBtn.setOnClickListener {
            val intent = Intent(this,WebViewActivity::class.java)
            intent.putExtra("link",resources.getString(R.string.teacher_feedback_link))
            startActivity(intent)
        }

        binding.alumniFeedbackBtn.setOnClickListener {
            val intent = Intent(this,WebViewActivity::class.java)
            intent.putExtra("link",resources.getString(R.string.alumni_feedback_link))
            startActivity(intent)
        }

        binding.parentFeedbackBtn.setOnClickListener {
            val intent = Intent(this,WebViewActivity::class.java)
            intent.putExtra("link",resources.getString(R.string.parent_feedback_link))
            startActivity(intent)
        }
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