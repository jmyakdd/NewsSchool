package jmy.com.newsschool.fragment

import android.location.Location
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.LocationSource
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.MyLocationStyle
import com.amap.api.maps.offlinemap.OfflineMapManager
import jmy.com.newsschool.R
import kotlinx.android.synthetic.main.fra_bbs.*

/**
 * Created by CRTE-CD-13 on 2018/5/18.
 */
class BbsFragment : Fragment(), AMap.OnMyLocationChangeListener, LocationSource, OfflineMapManager.OfflineMapDownloadListener {
    override fun onDownload(p0: Int, p1: Int, p2: String?) {

    }

    override fun onCheckUpdate(p0: Boolean, p1: String?) {

    }

    override fun onRemove(p0: Boolean, p1: String?, p2: String?) {

    }

    override fun deactivate() {

    }

    override fun activate(p0: LocationSource.OnLocationChangedListener?) {

    }

    override fun onMyLocationChange(p0: Location?) {

    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fra_bbs, null)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.onCreate(savedInstanceState)
        var map = mapView.map
        var locationStyle = MyLocationStyle()
        map.isMyLocationEnabled = true
        map.setOnMyLocationChangeListener(this)
        map.setLocationSource(this)
        map.moveCamera(CameraUpdateFactory.changeLatLng(LatLng(30.5702000000, 104.0647600000)))
        download.setOnClickListener {
//            startActivity(Intent(activity, OfflineMapActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}