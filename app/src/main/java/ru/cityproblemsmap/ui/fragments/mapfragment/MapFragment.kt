package ru.cityproblemsmap.ui.fragments.mapfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import ru.cityproblemsmap.api.model.GetPointsResponse
import ru.cityproblemsmap.ui.activities.mainactivity.MainActivityView
import ru.cityproblemsmap.ui.base.BaseFragment

class MapFragment : BaseFragment(), MapView {

    @InjectPresenter
    lateinit var presenter: MapPresenter

    companion object {
        const val TAG = "MapFragment"

        val START_POINT = Point(54.720430, 20.503067)
        const val START_ZOOM = 12f

        const val IMAGE_CAPTURE_REQUEST_CODE = 102
    }

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

    override fun onStart() {
        super.onStart()

        mapview.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()

        mapview.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            IMAGE_CAPTURE_REQUEST_CODE -> {
                if (data == null) {
                    Log.e("MapFragment", "image intent data is null")
                    return
                }

                presenter.onImageCaptured(context!!, data)
            }
        }
    }

    // ============================================================
    // UI Handlers
    // ============================================================

    private val btnAddPointClickListener = View.OnClickListener {
        presenter.addButtonClicked(this, IMAGE_CAPTURE_REQUEST_CODE)
    }

    // ============================================================
    // MapView
    // ============================================================

    override fun showPoints(points: GetPointsResponse) {
        val yandexPoints = mutableListOf<Point>()
        points.points.map {
            yandexPoints.add(Point(it.latitude, it.longitude))
        }

        mapview.map.mapObjects.clear()
        points.points.map {
            val marker = mapview.map.mapObjects.addPlacemark(Point(it.latitude, it.longitude))
            marker.addTapListener { _, _ ->
                presenter.onPointClicked(it)
                true
            }
        }

    }

    override fun passImageUriToAddPoint(uri: Uri) {
        (activity as? MainActivityView)?.onPhotoMade(uri)
    }

    // ============================================================
    // Private
    // ============================================================

    private fun initUI(view: View) {
        mapview.map.move(
                CameraPosition(START_POINT, START_ZOOM, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 0f),
                null
        )

        fab_add_point.setOnClickListener(btnAddPointClickListener)


    }

}