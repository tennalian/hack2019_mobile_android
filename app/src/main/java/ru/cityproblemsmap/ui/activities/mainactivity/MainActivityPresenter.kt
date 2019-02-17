package ru.cityproblemsmap.ui.activities.mainactivity

import android.Manifest.permission.CAMERA
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.cityproblemsmap.ui.fragments.mapfragment.MapFragment
import ru.cityproblemsmap.ui.fragments.sendpoint.SendPointFragment

@InjectViewState
class MainActivityPresenter : MvpPresenter<MainActivityView>() {

    val CAMERA_PERMISSION_REQUEST_CODE = 101

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        requestCameraPermission()
        openMapFragment()
    }

    private fun openSendPointFragment() {
        viewState.openFragment(SendPointFragment())
    }


    private fun openMapFragment() {
        viewState.openFragment(MapFragment())
    }

    private fun requestCameraPermission() {
        viewState.requestPermissions(CAMERA, CAMERA_PERMISSION_REQUEST_CODE)
    }

}