package ru.cityproblemsmap.ui.activities.mainactivity

import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.checkSelfPermission
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.cityproblemsmap.R

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

    override fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main, fragment)
                .commit()
    }

    override fun requestPermissions(permission: String, requestCode: Int) {

        // Permissions
        if (checkSelfPermission(this, permission) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            //TODO do something if denied again
        }
    }

}
