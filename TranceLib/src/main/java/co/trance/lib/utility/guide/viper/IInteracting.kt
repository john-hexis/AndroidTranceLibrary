package co.trance.lib.utility.guide.viper

import co.trance.lib.utility.guide.repository.IRepositoring

interface IInteracting {
    val repositories: List<IRepositoring>
    fun onLoaded()
    fun onDispose()
}