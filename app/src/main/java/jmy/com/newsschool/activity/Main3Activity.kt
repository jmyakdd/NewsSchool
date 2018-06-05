package jmy.com.newsschool.activity

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.*
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import jmy.com.newsschool.R
import jmy.com.newsschool.adapter.MapLoadAdapter
import jmy.com.newsschool.bean.ProviceData
import jmy.com.newsschool.data.MapData
import jmy.com.newsschool.util.MapCopyUtil
import jmy.com.newsschool.util.SPUtil
import kotlinx.android.synthetic.main.activity_main3.*
import java.io.File


class Main3Activity : AppCompatActivity() {
    private val usb_path = "/mnt/usb_storage/USB_DISK2/udisk0/map"
    private val path = Environment.getExternalStorageDirectory().absolutePath
    private val usb_path1 = path+"/amap/data/map"
            private var data = ArrayList<ProviceData>()
    private lateinit var dialog: ProgressDialog

    var permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INSTALL_SHORTCUT,
            Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        dialog = ProgressDialog(this)
        dialog.setTitle("导入中")
        dialog.setCancelable(false)
        btn.setOnClickListener {
            startActivity(Intent(this,Main4Activity::class.java))
        }
        init()
        for( p in permissions){
            if(ContextCompat.checkSelfPermission(this,p)!= PackageManager.PERMISSION_GRANTED){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    this.requestPermissions(permissions,100)
                    return
                }
            }
        }
    }

    private fun init() {
        rv.visibility = View.VISIBLE
        for (key in MapData.map.keys) {
            var proviceData = ProviceData()
            proviceData.proviceKey = key
            proviceData.proviceValue = MapData.map.get(key)
            var isLoad = SPUtil.get(this, key, false) as Boolean
            proviceData.isLoad = isLoad
            data.add(proviceData)
        }
        var adapter = MapLoadAdapter(data, this)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
        adapter.setOnMapMenuClickListener(object : MapLoadAdapter.OnMapMenuClickListener {
            override fun onStart(position: Int) {
                var file = File(usb_path + "/" + data.get(position).proviceKey)
                if (!file.exists()) {
                    Toast.makeText(this@Main3Activity, "下载失败，请检查是否插入u盘或者u盘中是否有该地图包", Toast.LENGTH_SHORT).show()
                    return
                }
                dialog.show()
                data.get(position).isLoading = true
                adapter.notifyDataSetChanged()
                Thread(object : Runnable {
                    override fun run() {
                        MapCopyUtil.copyFiles(data.get(position).proviceKey, usb_path + "/" + data.get(position).proviceKey,
                                path + "/amap/data/vmap")
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
                        MapCopyUtil.delete(path + "/amap/data/vmap", data.get(position).proviceKey)
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
                        SPUtil.put(this@Main3Activity, data.get(position).proviceKey, true)
                    }
                    1 -> {
                        data.get(position).isLoad = false
                        data.get(position).isLoading = false
                        SPUtil.put(this@Main3Activity, data.get(position).proviceKey, false)
                    }
                }
                rv.adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }
    }
}
