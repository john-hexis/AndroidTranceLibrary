package co.trance.lib.utility.base

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

@Suppress("DEPRECATION")
abstract class BaseFragment: Fragment() {
    /**
     * Toolbar, it's assign to [ToolbarOption.toolbarViewId] in [ToolbarOption]
     * */
    protected lateinit var toolbar: Toolbar

    /**
     * To set toolbar option. Override this variable to set toolbarOption.
     *
     * example:
     * ```
     * protected val toolbarOption: ToolbarOption by lazy {
     *      ToolbarOption(
     *          toolbarColor = ColorDrawable(ContextCompat.getColor(requireContext(), R.color.theme_color)),
     *          showTitleEnabled = true,
     *          homeAsUpEnable = true
     *      )
     * }
     * ```
     * */
    protected abstract var toolbarOption: ToolbarOption

    /**
     * Toolbar settings data class.
     * */
    data class ToolbarOption(
        val toolbarColor: ColorDrawable,
        val showTitleEnabled: Boolean,
        val homeAsUpEnable: Boolean,
        val toolbarViewId: Int
    )

    /**
     * Override this onViewCreated if you need to do custom toolbar option.
     * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.initToolbar(view, toolbarOption)
    }

    /**
     * Initialise app action toolbar when view is created, this method contains custom setup arguments. Only call this method if need to custom the app action toolbar appearance.
     *
     *  @param view current fragment view after view is created
     *  @param option toolbar setting properties
     */
    protected fun initToolbar(view: View, option: ToolbarOption) {
        toolbar = view.findViewById(option.toolbarViewId)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(option.showTitleEnabled)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(option.homeAsUpEnable)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            toolbar.setBackgroundDrawable(option.toolbarColor)
        } else {
            toolbar.background = option.toolbarColor
        }

        toolbar.setNavigationOnClickListener { (activity as BaseActivity).fragmentBackPressed() }
    }
}