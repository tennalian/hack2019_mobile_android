package ru.cityproblemsmap.ui.base

import android.os.Build
import android.support.annotation.ColorRes
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
import com.arellomobile.mvp.MvpAppCompatFragment
import ru.cityproblemsmap.R
import ru.cityproblemsmap.utils.DimensHelper

abstract class BaseFragment : MvpAppCompatFragment() {

    fun setupToolbar(
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

    fun setStatusBarColor(@ColorRes color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.window?.clearFlags(FLAG_TRANSLUCENT_STATUS)
            activity?.window?.statusBarColor = ResourcesCompat.getColor(resources, color, context?.theme)
        }
    }
}