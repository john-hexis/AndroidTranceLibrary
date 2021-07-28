package co.trance.lib.utility.base

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import co.trance.lib.utility.helper.Misc
import co.trance.lib.utility.helper.UITransitionOption

abstract class BaseActivity: AppCompatActivity() {
    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result -> onActivityResult(result) }

    //region Activity LifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        this.setupAnalytics()
    }

    protected abstract fun setupAnalytics()

    override fun onStart() {
        super.onStart()
        this.permissionHandler()
    }

    protected abstract fun permissionHandler()

    override fun onResume() {
        super.onResume()
        this.startAnalytics()
    }

    protected abstract fun startAnalytics()

    override fun onPause() {
        super.onPause()
        this.endAnalytics()
    }

    protected abstract fun endAnalytics()
    //endregion

    //region Activity Result / Intent Handler
    abstract fun onActivityResult(activityResult: ActivityResult)

    abstract fun onHandleActionIntent(intent: Intent?)
    //endregion

    //region Non-Overrides Methods
    protected fun replaceFragmentNextPageAnimation(newFragment: Fragment, option: UITransitionOption = UITransitionOption()) {
        this.replaceFragment(newFragment,
            option.idContainer,
            option.animateSlideInRight,
            option.animateSlideOutLeft,
            option.popInRight,
            option.popOutLeft)
    }

    protected fun replaceFragmentNoAnimation(newFragment: Fragment, option: UITransitionOption = UITransitionOption()) {
        this.replaceFragment(newFragment,
            option.idContainer)
    }

    private fun replaceFragment(newFragment: Fragment, idContainer: Int = 0, animIn: Int = 0, animOut: Int = 0, popIn: Int = 0, popOut: Int = 0) {
        val fm = supportFragmentManager

        val transaction = fm.beginTransaction()
        val backStateName = newFragment.javaClass.name

        if (animIn == 0 && animOut == 0 && popIn == 0 && popOut == 0) {
            transaction.setTransition(FragmentTransaction.TRANSIT_NONE)
        } else {
            transaction.setCustomAnimations(animIn, animOut, popIn, popOut)
        }

        val oldFragment = fm.findFragmentById(idContainer)

        if (oldFragment != null) {
            // avoid loadingDialog same fragment twice
            if (!oldFragment.javaClass.name.equals(newFragment.javaClass.name, ignoreCase = true)) {
                transaction.add(idContainer, newFragment, backStateName)
                transaction.hide(oldFragment)
            }
        } else {
            transaction.add(idContainer, newFragment, backStateName)
        }

        transaction.addToBackStack(backStateName)
        transaction.commit()
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
    class RequestCode {
        companion object Shared {
            const val NONE = 10001
            const val LOCATION_PERMISSION = 10002
            const val CAMERA_PERMISSION = 10003
            const val NETWORK_PERMISSION = 10004
        }
    }
    //endregion
}