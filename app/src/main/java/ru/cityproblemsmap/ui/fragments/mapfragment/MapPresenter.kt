package ru.cityproblemsmap.ui.fragments.mapfragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.yandex.mapkit.geometry.Point

@InjectViewState
class MapPresenter : MvpPresenter<MapView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showPoints(
            listOf(
                Point(54.751574, 20.573856),
                Point(54.761574, 20.583856)
            )
        )
    }

    fun addButtonClicked() {
        //TODO
    }


}