package jmy.com.newsschool.fragment

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jmy.com.newsschool.R
import jmy.com.newsschool.service.MyPlayingService
import kotlinx.android.synthetic.main.fra_mine.*

/**
 * Created by CRTE-CD-13 on 2018/5/18.
 */
class MineFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fra_mine, null)
        return view
    }

    var receiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Log.e("test", "recevie action")
        }
    }

    lateinit var binder:MyPlayingService.MyBinder
    var serviceConnection = object :ServiceConnection{
        override fun onServiceDisconnected(p0: ComponentName?) {

        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            binder = p1 as MyPlayingService.MyBinder
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start.setOnClickListener {
            activity.startService(Intent(activity, MyPlayingService::class.java))
        }

        stop.setOnClickListener {
            activity.stopService(Intent(activity, MyPlayingService::class.java))
        }

        bind.setOnClickListener {
            activity.bindService(Intent(activity,MyPlayingService::class.java),serviceConnection,
                    Context.BIND_AUTO_CREATE)
        }

        unbind.setOnClickListener {
            activity.unbindService(serviceConnection)
        }

        download.setOnClickListener {
            if(binder!=null){
                binder.startDownload()
            }
        }

        initReceiver()
    }

    private fun initReceiver() {
        var filter = IntentFilter()
        filter.addAction(MyPlayingService.ACTION_START_COMMAND)
        activity.registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        activity.unregisterReceiver(receiver)
    }
}