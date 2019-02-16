package ru.cityproblemsmap.ui.sendpoint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.cityproblemsmap.R

class SendPointFragment : MvpAppCompatFragment(), SendPointView {

    @InjectPresenter
    lateinit var presenter: SendPointPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_send_point, container, false)
    }
}