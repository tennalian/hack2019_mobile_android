package ru.cityproblemsmap.ui.fragments.sendpoint

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_send_point.*
import ru.cityproblemsmap.R
import ru.cityproblemsmap.ui.base.BaseFragment


class SendPointFragment : BaseFragment(), SendPointView {

    companion object {
        const val TAG = "SendPointFragment"
    }

    @InjectPresenter
    lateinit var presenter: SendPointPresenter

    private var imageUri: Uri? = null

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

    private val btnBackClickListener = View.OnClickListener {
        popFragment()
    }

    private val btnAddPhotoClickListener = View.OnClickListener {
        //        presenter.uploadPhoto(imageUri)
        presenter.onSendButtonPressed(
                et_title.text.toString(),
                et_description.text.toString(),
                imageUri
        )
    }

    // ============================================================
    // SendPointView
    // ============================================================

    override fun showPhoto(uri: Uri) {
        iv_photo.setImageURI(uri)
        imageUri = uri
    }

    override fun popFragment() {
        activity?.supportFragmentManager
                ?.popBackStack("SendPointFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun setButtonEnabled(enabled: Boolean) {
        if (!enabled)
            btn_send.setBackgroundColor(ResourcesCompat.getColor(resources, android.R.color.darker_gray, context?.theme))
    }

    // ============================================================
    // Private
    // ============================================================

    private fun initUI(view: View) {
        setupToolbar(view, R.string.send_point_screen_send_point_title, null, true, btnBackClickListener)
        setStatusBarColor(R.color.toolbarBackgroundSendPoint)

        btn_send.setOnClickListener(btnAddPhotoClickListener)

        checkBundle()
    }

    private fun checkBundle() {
        val photoUri = arguments?.getParcelable<Uri>("uri") ?: return
        presenter.onPhotoAdded(photoUri)
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