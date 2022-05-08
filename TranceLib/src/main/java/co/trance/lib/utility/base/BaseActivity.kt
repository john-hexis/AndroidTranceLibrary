package co.trance.lib.utility.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import co.trance.lib.utility.helper.Misc
import co.trance.lib.utility.helper.UITransitionOption

abstract class BaseActivity: AppCompatActivity(), IBaseActivity {
    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result -> onHandleActivityResult(result) }

    /**
     * Contain base layout container,
     * */
    protected var baseContainerView: View? = null

    //region Activity LifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setupView()
        this.setupAnalytics()
    }

    /**
     * Setup view handler.
     * */
    protected open fun setupView() {
        this.supportActionBar?.hide()
    }

    /**
     * Analytics setup handler, it's called during onCreate activity lifecycle.
     * */
    protected open fun setupAnalytics() {}

    override fun onStart() {
        super.onStart()
        this.permissionHandler()
    }

    /**
     * Permission handler, it's called during onStart activity lifecycle.
     * */
    protected abstract fun permissionHandler()

    override fun onResume() {
        super.onResume()
        this.startAnalytics()
    }

    /**
     * Start analytics handler, it's called during onStart activity lifecycle.
     * */
    protected open fun startAnalytics() {}

    override fun onPause() {
        super.onPause()
        this.endAnalytics()
    }

    /**
     * End analytics handler, it's called during onPause activity lifecycle.
     * */
    protected open fun endAnalytics() {}
    //endregion

    //region Activity Result / Intent Handler
    /**
     * Handle activity result callback
     * */
    abstract fun onHandleActivityResult(activityResult: ActivityResult)

    /**
     * Handle activity action result callback
     * */
    abstract fun onHandleActionIntent(intent: Intent?)
    //endregion

    //region Non-Overrides Methods
    @Deprecated(message = "Please use pushToNextFragment instead.", level = DeprecationLevel.ERROR)
    protected fun replaceFragmentNextPageAnimation(newFragment: Fragment, option: UITransitionOption = UITransitionOption()) {
        this.replaceFragment(newFragment,
            option.idContainer,
            option.animateSlideInRight,
            option.animateSlideOutLeft,
            option.popInRight,
            option.popOutLeft)
    }

    @Deprecated(message = "Please use pushToNextFragment instead.", level = DeprecationLevel.ERROR)
    protected fun replaceFragmentNoAnimation(newFragment: Fragment, option: UITransitionOption = UITransitionOption()) {
        this.replaceFragment(newFragment,
            option.idContainer)
    }

    /**
     * Navigate to next fragment.
     *
     * @param newFragment next fragment to be navigated
     * @param option transition settings, by default, it's optional to set
     * */
    protected fun pushNextFragment(newFragment: Fragment, option: UITransitionOption = UITransitionOption()) {
        this.addFragment(newFragment,
            option.idContainer,
            option.animateSlideInRight,
            option.animateSlideOutLeft,
            option.popInRight,
            option.popOutLeft)
    }

    private fun replaceFragment(fragment: Fragment, idContainer: Int = 0, animIn: Int = 0, animOut: Int = 0, popIn: Int = 0, popOut: Int = 0) {
        val fm = supportFragmentManager

        val transaction = fm.beginTransaction()

        if (animIn == 0 && animOut == 0 && popIn == 0 && popOut == 0) {
            transaction.setTransition(FragmentTransaction.TRANSIT_NONE)
        } else {
            transaction.setCustomAnimations(animIn, animOut, popIn, popOut)
        }

        transaction.addToBackStack(fragment.javaClass.name)
        transaction.replace(idContainer, fragment).commit()
        fm.executePendingTransactions()
    }

    private fun addFragment(newFragment: Fragment, idContainer: Int = 0, animIn: Int = 0, animOut: Int = 0, popIn: Int = 0, popOut: Int = 0) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()

        if (animIn == 0 && animOut == 0 && popIn == 0 && popOut == 0) {
            transaction.setTransition(FragmentTransaction.TRANSIT_NONE)
        } else {
            transaction.setCustomAnimations(animIn, animOut, popIn, popOut)
        }

        transaction.addToBackStack(newFragment.javaClass.name)
        transaction.add(idContainer, newFragment).commit()
        fm.executePendingTransactions()
    }

    protected fun loadDialogFragment(f: DialogFragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val ft = fragmentManager.beginTransaction()

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

        val prev = supportFragmentManager.findFragmentByTag(tag)
        if (prev != null) {
            ft.remove(prev)
        }
        f.show(ft, tag)

        Misc.hideSoftKeyboard(this)
    }

    protected fun startActivityWithAnimation(intent: Intent, option: UITransitionOption = UITransitionOption()){
        activityResultLauncher.launch(intent)
        overridePendingTransition(
            option.animateSlideInRight,
            option.animateSlideOutLeft)
    }
    //endregion

    //region Request Code
    inner class RequestCode {
            val NONE = 10001
            val LOCATION_PERMISSION = 10002
            val CAMERA_PERMISSION = 10003
            val NETWORK_PERMISSION = 10004
    }
    //endregion
}