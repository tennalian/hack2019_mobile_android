package ru.cityproblemsmap.ui.mainactivity

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.cityproblemsmap.ui.sendpoint.SendPointFragment
import ru.cityproblemsmap.ui.sendpoint.SendPointView

@InjectViewState
class MainActivityPresenter : MvpPresenter<MainActivityView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        openSendPointFragment()
    }

    private fun openSendPointFragment() {
        viewState.openFragment(SendPointFragment())
    }
}