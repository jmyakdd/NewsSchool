package jmy.com.newsschool.activity

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.SurfaceHolder
import jmy.com.newsschool.R
import jmy.com.newsschool.base.BaseTitleActivity
import kotlinx.android.synthetic.main.activity_vedio_play.*

class VedioPlayActivity : BaseTitleActivity() {
    val path = "http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8"
    override fun contentView(): Int {
        return R.layout.activity_vedio_play
    }

    var player:MediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBack()
        setTitle("视频播放")
        seekbar.max = 100
        seekbar.progress = 20
        seekbar.secondaryProgress = 50

        var holder = surface.holder
        player.setOnPreparedListener {
            player.start()
        }
        holder.addCallback(object :SurfaceHolder.Callback{
            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
                player.reset()
                player.setDisplay(holder)
                player.setDataSource(this@VedioPlayActivity,Uri.parse(path))
                player.prepareAsync()
            }

            override fun surfaceDestroyed(p0: SurfaceHolder?) {

            }

            override fun surfaceCreated(p0: SurfaceHolder?) {

            }
        })
    }
}