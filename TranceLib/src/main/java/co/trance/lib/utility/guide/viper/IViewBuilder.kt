package co.trance.lib.utility.guide.viper

import android.content.Context
import co.trance.lib.utility.guide.repository.IRepositoring

interface IViewBuilder<out V: IViewing<IPresenting<IInteracting>>, in P: IPresenting<IInteracting>> {
    fun build(): V
    fun setPresenter(presenter: P): IViewBuilder<V,P> { throw NotImplementedError() }
    fun setActivity(activity: Context): IViewBuilder<V,P> { throw NotImplementedError() }
}