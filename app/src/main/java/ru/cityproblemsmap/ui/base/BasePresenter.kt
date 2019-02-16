package ru.cityproblemsmap.ui.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.Disposable


abstract class BasePresenter<V : MvpView> : MvpPresenter<V>() {

    val disposables = mutableListOf<Disposable>()

    override fun detachView(view: V) {
        super.detachView(view)

        dispose()
    }

    private fun dispose() {
        disposables.map { it.dispose() }
    }

}