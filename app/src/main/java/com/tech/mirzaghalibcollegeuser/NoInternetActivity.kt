package com.tech.mirzaghalibcollegeuser

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.tech.mirzaghalibcollegeuser.databinding.ActivityNoInternetBinding
import com.tech.mirzaghalibcollegeuser.utils.util

class NoInternetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoInternetBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoInternetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.animationView.setAnimation(R.raw.no_connection)

        binding.animationView.playAnimation()

        binding.tryBtn.setOnClickListener {
            if(util.isNetworkAvailable(applicationContext)){
                binding.animationView.pauseAnimation()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
        }

    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {

        if(util.isNetworkAvailable(applicationContext)){
            binding.animationView.pauseAnimation()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        super.onResume()
    }
}