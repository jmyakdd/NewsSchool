package jmy.com.newsschool.base

import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseTitleActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    fun setBack(){
        if(supportActionBar!=null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    fun setTitle(str:String){
        tv_title.text = str
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                android.R.id.home -> finish()
            }
        }
        return true
    }
}