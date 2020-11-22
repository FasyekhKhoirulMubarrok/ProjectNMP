package com.kuliahnmp.projectnmp

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var fragments:ArrayList<Fragment> = ArrayList()
    var products:ArrayList<Product> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar : ActionBar? = supportActionBar
        val fragmentMan : FragmentManager? = supportFragmentManager

        //actionBar!!.setTitle(Html.fromHtml("<font color='black'>" + "Home" + "</font>"))
        actionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        actionBar!!.setCustomView(R.layout.actionbar)


        fragments.add(HomeFragment())
        fragments.add(CartFragment())
        fragments.add(HistoryFragment())
        fragments.add(ProfileFragment())

        viewPager.adapter = Adapter(this, fragments)

        //viewPager.currentItem = 3
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
                    } else if(it.itemId == R.id.itemCart) {
                        viewPager.currentItem = 1
                        fragmentMan!!.beginTransaction().detach(fragments[1]).attach(fragments[1]).commit()
                    }else if(it.itemId == R.id.itemHistory) {
                        viewPager.currentItem = 2
                        fragmentMan!!.beginTransaction().detach(fragments[2]).attach(fragments[2]).commit()
                    }else {
                        viewPager.currentItem = 3
                        //fragmentMan!!.beginTransaction().detach(fragments[3]).attach(fragments[3]).commit()
                    }
                    true

                }


            }



        })


    }
}