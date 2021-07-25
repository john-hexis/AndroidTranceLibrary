package co.trance.lib.utility.guide.viper

import android.content.Context

abstract class ViewBuilder<V: IViewing> {
    abstract fun build(): V
    abstract fun <P: IPresenting> setPresenter(presenter: P): ViewBuilder<V>
    abstract fun setActivity(activity: Context): ViewBuilder<V>
}