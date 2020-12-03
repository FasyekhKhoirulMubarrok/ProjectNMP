package com.kuliahnmp.projectnmp

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_layout.*


class MainActivity : AppCompatActivity() {

    var fragments:ArrayList<Fragment> = ArrayList()
    var products:ArrayList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //////////////////////
        val actionBar : ActionBar? = supportActionBar
        val fragmentMan : FragmentManager? = supportFragmentManager
        actionBar!!.setTitle(Html.fromHtml("<font color='black'>" + "Home" + "</font>"))
        //actionBar!!.setTitle(Html.fromHtml("<font color='black'>" + "Home" + "</font>"))
//        actionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
//        actionBar!!.setCustomView(R.layout.actionbar)
        setContentView(R.layout.drawer_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        var drawerToggle =
            ActionBarDrawerToggle(this, drawerLayout ,R.string.app_name,
                R.string.app_name)
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()

        fragments.add(HomeFragment())
        fragments.add(CartFragment())
        fragments.add(HistoryFragment())
        fragments.add(ProfileFragment())
        Log.e("isi",Global.histories.toString())
        viewPager.adapter = Adapter(this, fragments)

        viewPager.currentItem = 3
        viewPager.currentItem = 2
        viewPager.currentItem = 1
        viewPager.currentItem = 0


        viewPager.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val menu = arrayOf(R.id.itemHome, R.id.itemCart, R.id.itemHistory, R.id.itemProfile)
                bottomNav.selectedItemId=menu[position]
                bottomNav.setOnNavigationItemSelectedListener {
                    if(it.itemId == R.id.itemHome) {
                        viewPager.currentItem = 0
                        actionBar!!.setTitle(Html.fromHtml("<font color='black'>" + "Home" + "</font>"))
                    } else if(it.itemId == R.id.itemCart) {
                        viewPager.currentItem = 1
                        fragmentMan!!.beginTransaction().detach(fragments[1]).attach(fragments[1]).commit()
                        actionBar!!.setTitle(Html.fromHtml("<font color='black'>" + "Cart" + "</font>"))
                    }else if(it.itemId == R.id.itemHistory) {
                        viewPager.currentItem = 2
                        fragmentMan!!.beginTransaction().detach(fragments[2]).attach(fragments[2]).commit()
                        actionBar!!.setTitle(Html.fromHtml("<font color='black'>" + "History" + "</font>"))
                    }else {
                        viewPager.currentItem = 3
                        //fragmentMan!!.beginTransaction().detach(fragments[3]).attach(fragments[3]).commit()
                        actionBar!!.setTitle(Html.fromHtml("<font color='black'>" + "Profile" + "</font>"))
                    }
                    true

                }


            }



        })
        navView.setNavigationItemSelectedListener {

            if(it.itemId == R.id.itemHome) {
                viewPager.currentItem = 0
            } else if(it.itemId == R.id.itemCart) {
                viewPager.currentItem = 1
            }else if(it.itemId == R.id.itemHistory) {
                viewPager.currentItem = 2
            }else if(it.itemId == R.id.itemProfile){
                viewPager.currentItem = 3
            }else{
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                Global.users.clear()
                Global.histories.clear()
                Global.productHistories.clear()
                Global.productSementara.clear()
                Global.carts.clear()
                Global.qtyG = 0
                Global.subTotalHarga = 0
                Global.orderId =0
                Log.e("isiGLobal",Global.toString())
                finish()
            }
            drawerLayout.closeDrawer(GravityCompat.START)

            true

        }


    }
}