package jmy.com.newsschool.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.classic.adapter.CommonRecyclerAdapter
import jmy.com.newsschool.R
import jmy.com.newsschool.activity.VedioPlayActivity
import jmy.com.newsschool.adapter.NewsAdapter
import jmy.com.newsschool.base.BaseFragment
import kotlinx.android.synthetic.main.fra_home.*

/**
 * Created by CRTE-CD-13 on 2018/5/18.
 */
class HomeFragment : BaseFragment(), CommonRecyclerAdapter.OnItemClickListener {
    override fun onItemClick(viewHolder: RecyclerView.ViewHolder?, view: View?, position: Int) {
        startActivity(Intent(activity,VedioPlayActivity::class.java))
    }

    var data = ArrayList<String>()
    lateinit var adapter: NewsAdapter
    override fun contentView(): Int {
        return R.layout.fra_home
    }

    override fun initView() {
        for (i in 0..20) {
            data.add("item${i}")
        }
        adapter = NewsAdapter(activity, data)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        adapter.setOnItemClickListener(this)
    }
}