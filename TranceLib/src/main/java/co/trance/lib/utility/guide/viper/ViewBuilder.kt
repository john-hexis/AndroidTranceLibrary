package co.trance.lib.utility.guide.viper

import android.content.Context

abstract class ViewBuilder<V: IViewing> {
    abstract fun build(): V
    abstract fun setPresenter(presenter: IPresenting)
    abstract fun setActivity(activity: Context)
}