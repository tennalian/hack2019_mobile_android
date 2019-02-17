package ru.cityproblemsmap.ui.fragments.mapfragment

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.support.v4.app.Fragment
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import ru.cityproblemsmap.api.ApiClient
import ru.cityproblemsmap.api.model.GetPointData
import ru.cityproblemsmap.api.model.GetPointsResponse
import ru.cityproblemsmap.ui.base.BasePresenter
import java.io.ByteArrayOutputStream

@InjectViewState
class MapPresenter : BasePresenter<MapView>(), KoinComponent {

    companion object {
        const val TAG = "MapPresenter"
    }

    private val apiClient: ApiClient by inject()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

//        viewState.showPoints(
//                listOf(
//                        Point(54.751574, 20.573856),
//                        Point(54.761574, 20.583856)
//                )
//        )

        getCurrentPoints()
    }

    fun addButtonClicked(fragment: Fragment, requestCode: Int) {
        val intent = Intent(ACTION_IMAGE_CAPTURE)
        fragment.startActivityForResult(intent, requestCode)
    }

    fun onImageCaptured(context: Context, data: Intent) {
        val image = data.extras?.get("data") as? Bitmap

        if (image == null) {
            Log.e(TAG, "image is null")
            return
        }

        val uri = getImageUri(context, image)

        Log.w(TAG, "image captured: $image")
        Log.w(TAG, "image uri: $uri")

        viewState.passImageUriToAddPoint(uri)
    }

    private fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    private fun getCurrentPoints() {
        //TODO get group
        disposables.add(apiClient.getAllPoints()
                .subscribe({
                    showPoints(it)
                }) {
                    Log.e(TAG, "error getting points", it)
                })
    }

    private fun showPoints(points: GetPointsResponse) {
        viewState.showPoints(points)
    }

    fun onPointClicked(pointData: GetPointData) {

    }

}