package co.trance.lib.utility.base

import android.content.Intent

interface IBaseFragment {
    fun onNewIntent(intent: Intent?)
}