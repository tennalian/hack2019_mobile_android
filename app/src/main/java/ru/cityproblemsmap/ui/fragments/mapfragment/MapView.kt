package ru.cityproblemsmap.ui.fragments.mapfragment

import android.net.Uri
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.yandex.mapkit.geometry.Point

interface MapView : MvpView {

    //    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showPoints(points: List<Point>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun passImageUriToAddPoint(uri: Uri)

}