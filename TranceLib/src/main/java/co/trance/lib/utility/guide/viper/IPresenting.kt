package co.trance.lib.utility.guide.viper

interface IPresenting {
    val interactor: IInteracting?
    fun onLoaded()
    fun onDispose()
}