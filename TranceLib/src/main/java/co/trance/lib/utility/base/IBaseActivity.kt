package co.trance.lib.utility.base
import androidx.fragment.app.Fragment
interface IBaseActivity {
    fun fragmentBackPressed()
    fun fragmentNavigation(fragment: Fragment, backStack: Boolean ?= false, add: Boolean ?= false)
}