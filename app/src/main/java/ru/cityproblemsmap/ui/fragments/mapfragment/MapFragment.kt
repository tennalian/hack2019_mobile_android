package ru.cityproblemsmap.ui.fragments.mapfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import kotlinx.android.synthetic.main.fragment_map.*
import ru.cityproblemsmap.R
import ru.cityproblemsmap.ui.base.BaseFragment

class MapFragment : BaseFragment(), MapView {

    @InjectPresenter
    lateinit var presenter: MapPresenter

    // ============================================================
    // Fragment callbacks
    // ============================================================

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI(view)
    }

    override fun onDestroy() {
        super.onDestroy()

        mapview.onStop()
        MapKitFactory.getInstance().onStop()
    }

    // ============================================================
    // MapView
    // ============================================================

    override fun showPoints(points: List<Point>) {
        mapview.map.mapObjects.clear()
        points.map { mapview.map.mapObjects.addPlacemark(it) }

    }

    // ============================================================
    // Private
    // ============================================================

    private fun initUI(view: View) {
        mapview.onStart()
        MapKitFactory.getInstance().onStart()

        mapview.map.move(
            CameraPosition(
                Point(54.751574, 20.573856),
                11.0f, 0.0f, 0.0f
            ), Animation(Animation.Type.SMOOTH, 0f), null
        )
    }

}