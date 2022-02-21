package co.trance.lib.utility.guide.viper

import co.trance.lib.utility.guide.repository.IRepositoring

interface IInteracting<T:IRepositoring> {
    val repositories: List<T>
    fun onLoaded()
    fun onDispose()
}