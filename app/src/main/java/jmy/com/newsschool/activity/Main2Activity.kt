package jmy.com.newsschool.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import jmy.com.newsschool.R
import jmy.com.newsschool.base.BaseTitleActivity
import jmy.com.newsschool.customerview.BottomNavigationViewHelper
import jmy.com.newsschool.fragment.BbsFragment
import jmy.com.newsschool.fragment.HomeFragment
import jmy.com.newsschool.fragment.MineFragment
import jmy.com.newsschool.fragment.NewsFragment
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*
import kotlinx.android.synthetic.main.toolbar.*

class Main2Activity : BaseTitleActivity(), BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    override fun contentView(): Int {
        return R.layout.activity_main2
    }

    var homeFragment: HomeFragment? = null
    var newsFragment: NewsFragment? = null
    var bbsFragment: BbsFragment? = null
    var mineFragment: MineFragment? = null
    var fragments = ArrayList<Fragment>()

    var select = 0

    var map = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("首页")
        BottomNavigationViewHelper.disableShiftMode(bottom)
        bottom.setOnNavigationItemSelectedListener(this)
        initFragment()
        var fm = supportFragmentManager
        fm.beginTransaction().add(R.id.content, homeFragment).commit()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun initFragment() {
        homeFragment = HomeFragment()
        newsFragment = NewsFragment()
        bbsFragment = BbsFragment()
        mineFragment = MineFragment()
        fragments.add(homeFragment!!)
        fragments.add(newsFragment!!)
        fragments.add(bbsFragment!!)
        fragments.add(mineFragment!!)
    }

    private fun setFragment(i: Int) {
        if (select == i) {
            return
        }
        select = i
        var fm = supportFragmentManager
        when (i) {
            0 -> {
                fm.beginTransaction().replace(R.id.content, homeFragment).commit()
            }
            1 -> {
                fm.beginTransaction().replace(R.id.content, newsFragment).commit()
            }
            2 -> {
                fm.beginTransaction().replace(R.id.content, bbsFragment).commit()
            }
            3 -> {
                fm.beginTransaction().replace(R.id.content, mineFragment).commit()
            }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return true
    }*/

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }*/

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
            R.id.home -> {
                setTitle("首页")
                setFragment(0)
            }
            R.id.news -> {
                setTitle("趣闻")
                setFragment(1)
            }
            R.id.bbs -> {
                setTitle("论坛")
                setFragment(2)
            }
            R.id.mine -> {
                setTitle("我的")
                setFragment(3)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
