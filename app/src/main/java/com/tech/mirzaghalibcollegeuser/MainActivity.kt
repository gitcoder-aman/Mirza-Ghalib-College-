package com.tech.mirzaghalibcollegeuser

import android.content.ComponentName
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.tech.mirzaghalibcollegeuser.utils.util


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var toggle:ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustFontScale(resources.configuration);  //Lock font size of system setting
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        navController = Navigation.findNavController(this,R.id.fragment_layout)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigation_view)

        NavigationUI.setupWithNavController(bottomNavigationView,navController)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.start,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener(this) //drawer layout
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        val isNetwork = util.isNetworkAvailable(applicationContext)

        if(!isNetwork){
            startActivity(Intent(this,NoInternetActivity::class.java))
            finish()
        }
        super.onResume()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.navigation_developer->{
                // Create a PopupMenu for navigation item 3
                val popupMenu = PopupMenu(this, findViewById(R.id.navigation_developer))
                popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { popupMenuItem ->
                    when (popupMenuItem.itemId) {
                        R.id.instagram_item -> {
                            val username = resources.getString(R.string.insta_id)
                            val uri = Uri.parse("http://instagram.com/_u/$username")
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            intent.setPackage("com.instagram.android")

// Check if Instagram app is installed
                            if (intent.resolveActivity(packageManager) != null) {
                                startActivity(intent)
                            } else {
                                // Instagram app is not installed, open in browser
                                val browserIntent = Intent(Intent.ACTION_VIEW, uri)
                                startActivity(browserIntent)
                            }
                            true
                        }
                        R.id.linkedin_item -> {
                            val profileId = resources.getString(R.string.linkedin_id)
                            val intent = Intent(Intent.ACTION_VIEW)
                            val uri = Uri.parse("linkedin://profile/$profileId")
                            intent.data = uri
                            intent.setPackage("com.linkedin.android")

// Check if LinkedIn app is installed
                            if (intent.resolveActivity(packageManager) != null) {
                                startActivity(intent)
                            } else {
                                // LinkedIn app is not installed, open in browser
                                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/profile/view?id=$profileId"))
                                startActivity(browserIntent)
                            }

                            true
                        }
                        else -> false
                    }
                }
                popupMenu.setForceShowIcon(true) // display icons
                popupMenu.show()
                return true
            }
            R.id.navigation_ebook->{
                Toast.makeText(this,"Ebook",Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_rate->{
                Toast.makeText(this,"Rate the app",Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_share->{
                Toast.makeText(this, "Share app with your friends", Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_website->{
                val url = resources.getString(R.string.mirzaghalib_link)
                val intent = Intent(Intent.ACTION_VIEW)
                intent.component = ComponentName("com.android.chrome", "com.google.android.apps.chrome.Main")
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.popup_menu, menu)
        return true
    }
    private fun adjustFontScale(configuration: Configuration) {
        configuration.fontScale = 1.0.toFloat()
        val metrics = resources.displayMetrics
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density
        baseContext.resources.updateConfiguration(configuration, metrics)
    }
}