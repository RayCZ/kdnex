package com.example.kdnex

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.kdnex.fragment.CalculatorFragment
import com.example.kdnex.fragment.CanvasFragment
import com.example.kdnex.fragment.ClockFragment
import com.example.kdnex.fragment.ImageManipulationFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private var drawer: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private var left_navi: NavigationView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawer = findViewById(R.id.drawer)
        toolbar = findViewById(R.id.toolbar)
        left_navi = findViewById(R.id.left_navi)

        initDrawer()
        replaceFragment(CalculatorFragment())


    }

    fun initDrawer() {
        setSupportActionBar(toolbar)
        toolbar?.setNavigationOnClickListener {
            drawer?.openDrawer(GravityCompat.START)
        }

        val drawerToggle = ActionBarDrawerToggle(this@MainActivity, drawer, toolbar, R.string.open, R.string.close)
        drawer?.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        left_navi?.setNavigationItemSelectedListener { menuItem ->
            drawer?.closeDrawer(GravityCompat.START)
            when (menuItem.itemId) {
                R.id.navCalculator -> {
                    replaceFragment(CalculatorFragment())
                    true
                }

                R.id.navcCanvas -> {
                    replaceFragment(CanvasFragment())
                    true
                }

                R.id.navClock -> {
                    replaceFragment(ClockFragment())
                    true
                }

                R.id.navImage -> {
                    replaceFragment(ImageManipulationFragment())
                    true
                }
                else -> false
            }
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_layout, fragment)
            .commit()
    }

    override fun onBackPressed() {
        drawer?.let {
            if (it.isDrawerOpen(GravityCompat.START)) {
                it.closeDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }
        }


    }
}
