package ru.cityproblemsmap.ui.activities.mainactivity

import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.checkSelfPermission
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.cityproblemsmap.R
import ru.cityproblemsmap.api.model.GetPointData

class MainActivity : MvpAppCompatActivity(), MainActivityView {

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    // ============================================================
    // Activity callbacks
    // ============================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

    }

    // ============================================================
    // MainActivityView
    // ============================================================

    override fun openFragment(fragment: Fragment, fragmentName: String?) {
        val transaction = supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main, fragment)

        when (fragmentName) {
            null -> {
                transaction
                        .commit()
            }
            else ->
                transaction
                        .addToBackStack(fragmentName)
                        .commit()
        }

    }

    override fun requestPermissions(permission: String, requestCode: Int) {

        // Permissions
        if (checkSelfPermission(this, permission) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            //TODO do something if denied again
        }
    }

    override fun onPhotoMade(photoUri: Uri) {
        presenter.onPhotoMade(photoUri)
    }

    override fun onPointClicked(pointData: GetPointData) {
        presenter.onPointClicked(pointData)
    }

}
