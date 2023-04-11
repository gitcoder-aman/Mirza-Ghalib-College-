package com.tech.mirzaghalibcollegeuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var toggle:ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.navigation_developer->{
                Toast.makeText(this, "here developer", Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_ebook->{
                Toast.makeText(this,"Ebook",Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_theme->{
                Toast.makeText(this, "theme change", Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_rate->{
                Toast.makeText(this,"Rate the app",Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_share->{
                Toast.makeText(this, "Share app with your friends", Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_website->{
                Toast.makeText(this,"Mirza Ghalib website",Toast.LENGTH_SHORT).show()
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
}