package co.trance.lib.utility.guide.viper

import android.content.Context
import co.trance.lib.utility.guide.repository.IRepositoring

abstract class ViewBuilder<V: IViewing<IPresenting<IInteracting<IRepositoring>>>> {
    abstract fun build(): V
    open fun <P: IPresenting<IInteracting<IRepositoring>>> setPresenter(presenter: P): ViewBuilder<V> { throw NotImplementedError() }
    open fun setActivity(activity: Context): ViewBuilder<V> { throw NotImplementedError() }
}