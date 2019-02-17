package ru.cityproblemsmap.ui.fragments.sendpoint

import android.net.Uri
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import ru.cityproblemsmap.api.ApiClient
import ru.cityproblemsmap.ui.base.BasePresenter

@InjectViewState
class SendPointPresenter : BasePresenter<SendPointView>(), KoinComponent {

    private val apiClient: ApiClient by inject()

    fun onSendButtonPressed(title: String, description: String) {
        disposables.add(
            apiClient.sendPoint(54.1, 20.1, title, description)
                .subscribe({
                    Log.v("ApiClientImpl", "Got info: ${it::class.java}")
                }) {
                    Log.e("ApiClientImpl", "Error", it)
                }
        )
    }

    fun onPhotoAdded(photoUri: Uri) {
        viewState.showPhoto(photoUri)
    }
}
