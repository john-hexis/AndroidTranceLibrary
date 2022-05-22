package co.trance.lib.utility.guide.viper

import android.content.Context
import co.trance.lib.utility.guide.repository.IRepositoring

abstract class ViewBuilder<out V: IViewing<IPresenting<IInteracting>>> {
    abstract fun build(): V
    open fun <P: IPresenting<IInteracting>> setPresenter(presenter: P): ViewBuilder<V> { throw NotImplementedError() }
    open fun setActivity(activity: Context): ViewBuilder<V> { throw NotImplementedError() }
}