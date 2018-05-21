package jmy.com.newsschool.activity

import android.os.Bundle
import jmy.com.newsschool.R
import jmy.com.newsschool.base.BaseTitleActivity

class VedioPlayActivity : BaseTitleActivity() {
    override fun contentView(): Int {
        return R.layout.activity_vedio_play
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBack()
        setTitle("视频播放")
    }
}