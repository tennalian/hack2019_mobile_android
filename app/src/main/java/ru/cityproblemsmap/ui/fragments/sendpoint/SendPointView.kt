package ru.cityproblemsmap.ui.fragments.sendpoint

import android.net.Uri
import com.arellomobile.mvp.MvpView

interface SendPointView : MvpView {

    fun showPhoto(uri: Uri)

}