package co.trance.lib.utility.guide.viper

import android.content.Context

abstract class ViewBuilder<V: IViewing> {
    abstract fun build(): V
    open fun <P: IPresenting> setPresenter(presenter: P): ViewBuilder<V> { throw NotImplementedError() }
    open fun setActivity(activity: Context): ViewBuilder<V> { throw NotImplementedError() }
}