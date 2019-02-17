package ru.cityproblemsmap.ui.activities.mainactivity

import android.net.Uri
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpView

interface MainActivityView : MvpView {

    fun openFragment(fragment: Fragment)

    fun requestPermissions(permission: String, requestCode: Int)

    fun onPhotoMade(photoUri: Uri)

}