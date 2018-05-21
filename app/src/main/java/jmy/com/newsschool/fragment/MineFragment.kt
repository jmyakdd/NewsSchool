package jmy.com.newsschool.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jmy.com.newsschool.R
import kotlinx.android.synthetic.main.fra_mine.*

/**
 * Created by CRTE-CD-13 on 2018/5/18.
 */
class MineFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fra_mine, null)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv.text = this.javaClass.name
    }
}