package co.trance.lib.utility.base

import android.content.Intent
import android.location.Location
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment

/**
 * Interface which to be applied to [BaseActivity].
 * */
interface IBaseActivity {
    fun fragmentBackPressed()
    fun fragmentBackPressed(requestCode: Int, intent: Intent)
    fun fragmentNavigation(fragment: Fragment, backStack: Boolean ?= false, add: Boolean ?= false)
    fun getBaseActivity(): BaseActivity
    fun getDeviceLocation(success: (Location) -> Unit, failed: () -> Unit)
    fun isLocationEnabled(): Boolean

    /**
     * Interface for [Intent] action variant which to be applied to [BaseActivity].
     * */
    interface Action {
        /**
         * Get TAG value from determined action. TAG value usually is used to make segregate action handling.
         * */
        fun getTag(): String

        /**
         * Interface for [Intent] action variant which to be applied to [BaseActivity].
         * */
        interface Companion<T: Action> {
            /**
             * Parse TAG string to [Action].
             *
             * @param tag string tag of [Action].
             * */
            fun parseTag(tag: String): T
        }
    }
}