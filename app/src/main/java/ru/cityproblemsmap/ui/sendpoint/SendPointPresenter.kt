package ru.cityproblemsmap.ui.sendpoint

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import ru.cityproblemsmap.api.ApiClient

@InjectViewState
class SendPointPresenter : MvpPresenter<SendPointView>(), KoinComponent {

    private val apiClient: ApiClient by inject()

    fun onSendButtonPressed(title: String, description: String) {
        apiClient.sendPoint(54.1, 20.1, title, description)
    }

}