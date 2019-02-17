package ru.cityproblemsmap.ui.activities.mainactivity

import android.net.Uri
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MainActivityView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openFragment(fragment: Fragment, fragmentName: String? = null)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun requestPermissions(permission: String, requestCode: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onPhotoMade(photoUri: Uri)

}