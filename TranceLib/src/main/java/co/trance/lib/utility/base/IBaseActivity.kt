package co.trance.lib.utility.guide.viper
import androidx.fragment.app.Fragment
interface IBaseActivity {
    fun fragmentBackPressed()
    fun fragmentNavigation(fragment: Fragment, backStack: Boolean ?= false, add: Boolean ?= false)
}