package co.trance.lib.utility.guide.viper

import co.trance.lib.utility.guide.repository.IRepositoring

interface IPresenting<out T:IInteracting> {
    val interactor: T?
    fun onLoaded()
    fun onDispose()
    fun onDetach()
}