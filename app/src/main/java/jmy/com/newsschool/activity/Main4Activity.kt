package jmy.com.newsschool.activity

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.LocationSource
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.MyLocationStyle
import com.amap.api.maps.offlinemap.OfflineMapActivity
import jmy.com.newsschool.R
import kotlinx.android.synthetic.main.fra_bbs.*

class Main4Activity : AppCompatActivity(), AMap.OnMyLocationChangeListener, LocationSource {
    override fun deactivate() {

    }

    override fun activate(p0: LocationSource.OnLocationChangedListener?) {

    }

    override fun onMyLocationChange(p0: Location?) {

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fra_bbs)
        mapView.onCreate(savedInstanceState)
        var map = mapView.map
        var locationStyle = MyLocationStyle()
        map.isMyLocationEnabled = true
        map.setOnMyLocationChangeListener(this)
        map.setLocationSource(this)
        map.moveCamera(CameraUpdateFactory.changeLatLng(LatLng(30.5702000000, 104.0647600000)))
        download.setOnClickListener {
            startActivity(Intent(this, OfflineMapActivity::class.java))
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
