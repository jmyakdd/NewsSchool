package jmy.com.newsschool.fragment

import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import jmy.com.newsschool.R
import jmy.com.newsschool.Test
import jmy.com.newsschool.adapter.TreeMenuRvAdapter
import jmy.com.newsschool.bean.Menu
import kotlinx.android.synthetic.main.fra_news.*


/**
 * Created by CRTE-CD-13 on 2018/5/18.
 */
class NewsFragment : Fragment(), OnPageChangeListener, OnLoadCompleteListener {

    val path = Environment.getExternalStorageDirectory().toString() + "/info.doc"

    var data = ArrayList<Menu>()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pdfView.fromAsset("info.pdf").defaultPage(0)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .spacing(0) // in dp
                .load()
        rv.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        var adapter = TreeMenuRvAdapter(data, activity)
        rv.adapter = adapter
        adapter.setOnMenuItemClickListener { position: Int ->
            pdfView.jumpTo(data.get(position).page)
        }
        for (i in 0..Test.data.size-1) {
            var menu = Menu(Test.data.get(i)[0], Test.data.get(i)[1].toInt(), Test.data.get(i)[2].toInt())
            data.add(menu)
        }
        adapter.notifyDataSetChanged()
    }

    /**
     * Called when the PDF is loaded
     * @param nbPages the number of pages in this PDF file
     */
    override fun loadComplete(nbPages: Int) {

    }

    /**
     * Called when the user use swipe to change page
     *
     * @param page      the new page displayed, starting from 0
     * @param pageCount the total page count
     */
    override fun onPageChanged(page: Int, pageCount: Int) {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fra_news, null)
        return view
    }
}