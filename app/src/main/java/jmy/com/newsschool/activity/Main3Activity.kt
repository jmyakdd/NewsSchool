package jmy.com.newsschool.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import jmy.com.newsschool.R
import jmy.com.newsschool.adapter.MapLoadAdapter
import jmy.com.newsschool.bean.ProviceData
import jmy.com.newsschool.data.MapData
import jmy.com.newsschool.util.MapCopyUtil
import jmy.com.newsschool.util.SPUtil
import kotlinx.android.synthetic.main.activity_main3.*


class Main3Activity : AppCompatActivity() {
    private val usb_path = "/mnt/usb_storage/USB_DISK2/udisk0"
    private val path = Environment.getExternalStorageDirectory().absolutePath
    private var data = ArrayList<ProviceData>()
    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        dialog = ProgressDialog(this)
        dialog.setTitle("标题")
        dialog.setMessage("信息")
        dialog.setCancelable(false)
        for (key in MapData.map.keys) {
            var proviceData = ProviceData()
            proviceData.proviceKey = key
            proviceData.proviceValue = MapData.map.get(key)
            var isLoad: Boolean = SPUtil.get(this, proviceData.proviceValue, false) as Boolean
            proviceData.isLoad = isLoad
            data.add(proviceData)
        }
        var adapter = MapLoadAdapter(data, this)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
        adapter.setOnMapMenuClickListener(object : MapLoadAdapter.OnMapMenuClickListener {
            override fun onStart(position: Int) {
                dialog.show()
                data.get(position).isLoading = true
                adapter.notifyDataSetChanged()
                Thread(object : Runnable {
                    override fun run() {
                        MapCopyUtil.copyFiles(path + "/amap/data/map/" + data.get(position).proviceValue,
                                path + "/map/" + data.get(position).proviceValue)
                        Thread.sleep(1000)
                        var msg = Message()
                        msg.what = 0
                        msg.obj = position
                        handler.sendMessage(msg)
                    }
                }).start()
            }

            override fun onLongClick(position: Int) {
                dialog.show()
                Thread(object : Runnable {
                    override fun run() {
                        MapCopyUtil.delete(path + "/map/" + data.get(position).proviceValue)
                        Thread.sleep(1000)
                        var msg = Message()
                        msg.what = 1
                        msg.obj = position
                        handler.sendMessage(msg)
                    }
                }).start()
            }
        })
    }

    val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg != null) {
                var position = msg.obj as Int
                when (msg.what) {
                    0 -> {
                        data.get(position).isLoad = true
                        data.get(position).isLoading = false
                        SPUtil.put(this@Main3Activity, data.get(position).proviceValue, true)
                    }
                    1 -> {
                        data.get(position).isLoad = false
                        data.get(position).isLoading = false
                        SPUtil.put(this@Main3Activity, data.get(position).proviceValue, false)
                    }
                }
                rv.adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }
    }
}
