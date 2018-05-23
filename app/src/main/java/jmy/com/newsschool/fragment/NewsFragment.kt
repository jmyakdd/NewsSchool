package jmy.com.newsschool.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import jmy.com.newsschool.R
import kotlinx.android.synthetic.main.fra_news.*
import java.io.File


/**
 * Created by CRTE-CD-13 on 2018/5/18.
 */
class NewsFragment :Fragment(), OnPageChangeListener, OnLoadCompleteListener {
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
    val path = Environment.getExternalStorageDirectory().toString()+"/info.doc"
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*web.settings.javaScriptEnabled = true
        web.settings.useWideViewPort = true
        web.loadUrl("file:///android_asset/info.html")*/
        /*var path = "file:///andorid_asset/use_info.doc"
        var file = File(path)
        openFile(file)

        copy.setOnClickListener{
            FileStorage.writeBytesToFile(context.assets.open("use_info.doc"),
                    File(path))
        }

        open.setOnClickListener {
            openFile(File(path))
        }*/
        /*pdfView.fromAsset("info.pdf")
                .defaultPage(1)
                .load()*/
        pdfView.fromAsset("info.pdf").defaultPage(0)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .spacing(0) // in dp
                .load()
    }

    /**
     *
     * `openFile`
     *
     * @description: TODO(打开附件)
     * @param context
     * @param file
     * @since 2012-5-19 liaoyp
     */
    fun openFile(file: File) {
        try {
            val intent = Intent()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // 设置intent的Action属性
            intent.action = Intent.ACTION_VIEW
            // 设置intent的data和Type属性。
            var uri:Uri?=null
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(activity,"jmy.com.newsschool.FileProvider",file)
            }else{
                uri = Uri.fromFile(file)
            }*/
            uri = Uri.fromFile(file)
            intent.setDataAndType(/* uri */uri, "application/msword")
            // 跳转
            startActivity(intent)
            // Intent.createChooser(intent, "请选择对应的软件打开该附件！");
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "sorry附件不能打开，请下载相关软件！", Toast.LENGTH_SHORT).show()
        }

    }
}