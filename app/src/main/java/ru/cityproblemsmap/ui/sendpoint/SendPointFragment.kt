package ru.cityproblemsmap.ui.sendpoint

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_send_point.*
import ru.cityproblemsmap.R
import ru.cityproblemsmap.utils.DimensHelper


class SendPointFragment : MvpAppCompatFragment(), SendPointView {

    @InjectPresenter
    lateinit var presenter: SendPointPresenter

    // ============================================================
    // Fragment callbacks
    // ============================================================

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_send_point, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(view)
    }

    // ============================================================
    // UI
    // ============================================================

    private val btnBackClickListener = View.OnClickListener { }

    private val btnAddPhotoClickListener = View.OnClickListener {
        presenter.onSendButtonPressed(
                et_title.text.toString(),
                et_description.text.toString())
    }

    // ============================================================
    // Private
    // ============================================================

    private fun initUI(view: View) {
        setupToolbar(view, R.string.send_point_title, null, true, btnBackClickListener)

        btn_send.setOnClickListener(btnAddPhotoClickListener)
    }

    private fun setupToolbar(
            inflatedView: View, titleId: Int?, homeIconId: Int?, backButtonEnabled: Boolean,
            toolbarNavigationButtonClickListener: View.OnClickListener?
    ) {
        setupToolbar(
                inflatedView,
                titleId?.let { resources.getString(it) },
                homeIconId,
                backButtonEnabled,
                toolbarNavigationButtonClickListener
        )
    }


    private fun setupToolbar(
            inflatedView: View, title: String?, homeIconId: Int?, backButtonEnabled: Boolean,
            toolbarNavigationButtonClickListener: View.OnClickListener?
    ) {
        val toolbar = inflatedView.findViewById<Toolbar>(R.id.toolbar) ?: return

        val activity = activity as? AppCompatActivity ?: return

        activity.setSupportActionBar(toolbar)
        activity.supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar.title = title ?: ""
        when {
            homeIconId != null ->
                toolbar.setNavigationIcon(homeIconId)
            backButtonEnabled ->
                activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            else -> {
                activity.supportActionBar!!.setDisplayShowHomeEnabled(false)
                toolbar.setContentInsetsRelative(
                        toolbar.contentInsetLeft +
                                DimensHelper.dpToPx(context!!, 0.0f).toInt(),
                        toolbar.contentInsetRight
                )
            }
        }
        activity.setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener(toolbarNavigationButtonClickListener)
    }

//
//    fun setStatusBarColor(statusBar: View, color: Int) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            val w = activity?.window ?: return
//            w.setFlags(
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//            )
//            //status bar height
//            val actionBarHeight = getActionBarHeight()
//            val statusBarHeight = getStatusBarHeight()
//            //action bar height
//            statusBar.layoutParams.height = actionBarHeight + statusBarHeight
//            statusBar.setBackgroundColor(color)
//        }
//    }
//
//    private fun getActionBarHeight(): Int {
//        var actionBarHeight = 0
//        val tv = TypedValue()
//        if (activity?.theme?.resolveAttribute(android.R.attr.actionBarSize, tv, true)!!) {
//            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
//        }
//        return actionBarHeight
//    }
//
//    private fun getStatusBarHeight(): Int {
//        var result = 0
//        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
//        if (resourceId > 0) {
//            result = resources.getDimensionPixelSize(resourceId)
//        }
//        return result
//    }

}