package co.trance.lib.utility.base

import android.content.Intent
import android.location.Location
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment

interface IBaseActivity {
    fun fragmentBackPressed()
    fun fragmentBackPressed(requestCode: Int, intent: Intent)
    fun fragmentNavigation(fragment: Fragment, backStack: Boolean ?= false, add: Boolean ?= false)
    fun startActivityWithResult(intent: Intent, options: ActivityOptionsCompat? = null)
    fun getBaseActivity(): BaseActivity
    fun getDeviceLocation(success: (Location) -> Unit, failed: () -> Unit)
    fun isLocationEnabled(): Boolean

    interface Action {
        /**
         * Get TAG value from determined action. TAG value usually is used to make segregate action handling.
         * */
        fun getTag(): String
    }
}