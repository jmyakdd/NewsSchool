package jmy.com.newsschool.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import jmy.com.newsschool.R
import jmy.com.newsschool.base.BaseActivity
import jmy.com.newsschool.customerview.BottomNavigationViewHelper
import jmy.com.newsschool.fragment.BbsFragment
import jmy.com.newsschool.fragment.HomeFragment
import jmy.com.newsschool.fragment.MineFragment
import jmy.com.newsschool.fragment.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun contentView(): Int {
        return R.layout.activity_main
    }

    var homeFragment: HomeFragment? = null
    var newsFragment: NewsFragment? = null
    var bbsFragment: BbsFragment? = null
    var mineFragment: MineFragment? = null
    var fragments = ArrayList<Fragment>()

    var select = 0

    var map = HashMap<String,String>()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                toolbar.setTitle("首页")
                setFragment(0)
            }
            R.id.news -> {
                toolbar.setTitle("趣闻")
                setFragment(1)
            }
            R.id.bbs -> {
                toolbar.setTitle("论坛")
                setFragment(2)
            }
            R.id.mine -> {
                toolbar.setTitle("我的")
                setFragment(3)
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BottomNavigationViewHelper.disableShiftMode(bottom)
        bottom.setOnNavigationItemSelectedListener(this)
        toolbar.setTitle("首页")
        initFragment()
        var fm = supportFragmentManager
        fm.beginTransaction().add(R.id.content, homeFragment).commit()

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
}
