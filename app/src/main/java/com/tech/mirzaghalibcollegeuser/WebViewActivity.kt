package com.tech.mirzaghalibcollegeuser

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class WebViewActivity : AppCompatActivity() {

    private lateinit var webView:WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        supportActionBar?.hide()
        webView = findViewById(R.id.webView)

        val link = intent.getStringExtra("link")

        if (link != null) {
            webView.loadUrl(link)
        }else{
            Toast.makeText(this, "Url is not exist!", Toast.LENGTH_SHORT).show()
        }
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

    }
}