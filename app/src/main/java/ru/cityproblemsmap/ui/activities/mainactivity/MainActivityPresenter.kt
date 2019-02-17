package ru.cityproblemsmap.ui.activities.mainactivity

import android.Manifest.permission.*
import android.net.Uri
import android.os.Bundle
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.cityproblemsmap.ui.fragments.mapfragment.MapFragment
import ru.cityproblemsmap.ui.fragments.sendpoint.SendPointFragment

@InjectViewState
class MainActivityPresenter : MvpPresenter<MainActivityView>() {

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 1001
        private const val EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 1002
        private const val GPS_PERMISSION_REQUEST_CODE = 1003
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        requestCameraPermission()
        requestExternalStorageWritePermission()
        requestGpsPermissions()

        openMapFragment()
    }


    fun onPhotoMade(photoUri: Uri) {
        openSendPointFragment(photoUri)
    }

    private fun openSendPointFragment(photoUri: Uri?) {
        if (photoUri == null) {
            viewState.openFragment(SendPointFragment(), "SendPointFragment")
            return
        }

        val fragment = SendPointFragment()
        val args = Bundle()
        args.putParcelable("uri", photoUri)
        fragment.arguments = args
        viewState.openFragment(fragment, "SendPointFragment")
    }


    private fun openMapFragment() {
        viewState.openFragment(MapFragment())
    }

    private fun requestCameraPermission() {
        viewState.requestPermissions(CAMERA, CAMERA_PERMISSION_REQUEST_CODE)
    }

    private fun requestExternalStorageWritePermission() {
        viewState.requestPermissions(WRITE_EXTERNAL_STORAGE, EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE)
    }

    private fun requestGpsPermissions() {
        viewState.requestPermissions(ACCESS_FINE_LOCATION, GPS_PERMISSION_REQUEST_CODE)
    }

}