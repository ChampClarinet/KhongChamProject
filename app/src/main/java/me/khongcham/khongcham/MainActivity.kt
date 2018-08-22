package me.khongcham.khongcham

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import me.khongcham.khongcham.Adapters.AppFragmentAdapter


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

       /* val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        */

        nav_view.setNavigationItemSelectedListener(this)
        navigation_main.setOnNavigationItemSelectedListener(onNavigationSelectedListener)

        app_pager.adapter = AppFragmentAdapter(supportFragmentManager)

        app_pager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, p1: Float, p2: Int) {}
            override fun onPageSelected(position: Int) {
                setCurrentPage(position)
            }
        })

    }

    override fun onResume() {
        super.onResume()
        setCurrentPage(2)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private val onNavigationSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_stock -> {
                app_pager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_pay_bill -> {
                app_pager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_home -> {
                app_pager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_summary -> {
                app_pager.currentItem = 3
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_faq -> {
                app_pager.currentItem = 4
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

    private fun setCurrentPage(page: Int) {
        app_pager.currentItem = page
        var id = 2
        when (page) {
            0 -> id = R.id.navigation_stock
            1 -> id = R.id.navigation_pay_bill
            2 -> id = R.id.navigation_home
            3 -> id = R.id.navigation_summary
            4 -> id = R.id.navigation_faq
        }
        navigation_main.selectedItemId = id
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
