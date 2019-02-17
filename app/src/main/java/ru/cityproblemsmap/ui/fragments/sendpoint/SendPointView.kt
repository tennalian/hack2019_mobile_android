package ru.cityproblemsmap.ui.fragments.sendpoint

import android.net.Uri
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface SendPointView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showPhoto(uri: Uri)

}