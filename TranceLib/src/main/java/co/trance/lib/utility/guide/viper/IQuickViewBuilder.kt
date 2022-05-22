package co.trance.lib.utility.guide.viper

import android.content.Context

/**
 * Interface to guide of view builder design pattern without [IPresenting] support.
 * */
interface IQuickViewBuilder<out V: IViewing<IPresenting<IInteracting>>> {
    fun build(): V
    fun setActivity(activity: Context): IQuickViewBuilder<V> { throw NotImplementedError() }
}