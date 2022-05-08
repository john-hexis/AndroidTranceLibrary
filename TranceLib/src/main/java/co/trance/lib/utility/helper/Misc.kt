package co.trance.lib.utility.helper

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class Misc {
    companion object {
        /**
         * Hide keyboard from screen.
         *
         * @param activity current active activity
         * */
        fun hideSoftKeyboard(activity: Activity) {
            val inputMethodManager = activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            if (activity.currentFocus != null)
                inputMethodManager
                    .hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
            activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
        /**
         * Hide keyboard from screen.
         *
         * @param view current active view
         * */
        fun hideSoftKeyboard(view: View) {
            val inputMethodManager = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
        /**
         * Show keyboard on screen.
         *
         * @param activity current active activity
         * */
        fun showSoftKeyboard(activity: Activity) {
            val inputMethodManager = activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            if (activity.currentFocus != null)
                inputMethodManager
                    .toggleSoftInputFromWindow(
                        activity.currentFocus!!.windowToken,
                        InputMethodManager.SHOW_FORCED,
                        0
                    )
        }
        /**
         * Show snack bar on screen.
         *
         * @param view view which will contain snack bar
         * @param text text message
         * */
        fun showSnackBar(view: View?, text: String?) {
            val snackBarText = SpannableStringBuilder()
            snackBarText.append(text)
            snackBarText.setSpan(
                ForegroundColorSpan(Color.WHITE),
                0,
                snackBarText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            Snackbar.make(view!!, snackBarText, Snackbar.LENGTH_SHORT).show()
        }
        /**
         * Show toast message on center of screen.
         *
         * @param context current active activity context
         * @param text text message
         * */
        fun showToastInCenter(context: Context?, text: String?) {
            val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
        /**
         * Show toast message on bottom part of screen.
         *
         * @param context current active activity context
         * @param text text message
         * @param bottomMargin toast message bottom margin from bottom screen
         * */
        fun showToastAtBottom(context: Context?, text: String?, bottomMargin: Int) {
            val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM, 0, bottomMargin)
            toast.show()
        }
    }
}