package jmy.com.newsschool.adapter

import android.content.Context
import com.classic.adapter.BaseAdapterHelper
import com.classic.adapter.CommonRecyclerAdapter
import jmy.com.newsschool.R

class NewsAdapter(context: Context, data: ArrayList<String>) :
        CommonRecyclerAdapter<String>(context, R.layout.item_string, data) {
    /**
     * 数据更新回调
     *
     * @param helper [BaseAdapterHelper]
     * @param item 当前Item对象
     * @param position 下标
     */
    override fun onUpdate(helper: BaseAdapterHelper?, item: String?, position: Int) {
        if (helper != null) {
            helper.setText(R.id.item, item)
        }
    }

}