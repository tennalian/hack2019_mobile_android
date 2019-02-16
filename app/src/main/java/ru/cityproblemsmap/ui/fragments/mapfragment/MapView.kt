package ru.cityproblemsmap.ui.fragments.mapfragment

import com.arellomobile.mvp.MvpView
import com.yandex.mapkit.geometry.Point

interface MapView : MvpView {

    fun showPoints(points: List<Point>)

}