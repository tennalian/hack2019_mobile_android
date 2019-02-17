package ru.cityproblemsmap.ui.fragments.mapfragment

import android.net.Uri
import com.arellomobile.mvp.MvpView
import com.yandex.mapkit.geometry.Point

interface MapView : MvpView {

    fun showPoints(points: List<Point>)

    fun passImageUriToAddPoint(uri: Uri)

}