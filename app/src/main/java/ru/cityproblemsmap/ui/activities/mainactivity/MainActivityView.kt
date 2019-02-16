package ru.cityproblemsmap.ui.activities.mainactivity

import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpView

interface MainActivityView : MvpView {

    fun openFragment(fragment: Fragment)

}