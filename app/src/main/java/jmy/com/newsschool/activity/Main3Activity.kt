package jmy.com.newsschool.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import jmy.com.newsschool.R
import jmy.com.newsschool.data.MapData
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class Main3Activity : AppCompatActivity() {
    private val ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION"
    private val usb_path = "/mnt/usb_storage/USB_DISK2/udisk0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

//        copyFile(usb_path + "/test", Environment.getExternalStorageDirectory().absolutePath + "/test")

        Log.e("test", MapData.map.size.toString())
    }

    private fun copyResourse(fromFile: String, toFile: String) {
        var file = File(fromFile)
        if (!file.exists()) {
            Log.e("test", "源目录不存在")
            return
        }
        var file1 = File(toFile)
        if (!file1.exists()) {
            file1.mkdirs()
        }
        var files = file.listFiles()
        for (f in files) {
            copyFiles(f.absolutePath, toFile + "/" + f.name)
        }
    }

    private fun copyFiles(fromFile: String, toFile: String) {
        try {
            var input = FileInputStream(fromFile)
            var output = FileOutputStream(toFile)
            var b = ByteArray(1024)
            while (input.read(b) > 0) {
                output.write(b, 0, b.size)
            }
            input.close()
            output.close()
        } catch (e: Exception) {

        }
    }

}
