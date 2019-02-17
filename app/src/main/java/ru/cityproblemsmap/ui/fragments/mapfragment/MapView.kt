package ru.cityproblemsmap.ui.fragments.mapfragment

import android.net.Uri
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.cityproblemsmap.api.model.GetPointsResponse

interface MapView : MvpView {

    //    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showPoints(points: GetPointsResponse)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun passImageUriToAddPoint(uri: Uri)

}