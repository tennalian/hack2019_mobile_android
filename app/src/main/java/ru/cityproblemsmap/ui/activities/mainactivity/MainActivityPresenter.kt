package ru.cityproblemsmap.ui.activities.mainactivity

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.cityproblemsmap.ui.fragments.mapfragment.MapFragment
import ru.cityproblemsmap.ui.fragments.sendpoint.SendPointFragment

@InjectViewState
class MainActivityPresenter : MvpPresenter<MainActivityView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        openMapFragment()
    }

    private fun openSendPointFragment() {
        viewState.openFragment(SendPointFragment())
    }


    private fun openMapFragment() {
        viewState.openFragment(MapFragment())
    }

}