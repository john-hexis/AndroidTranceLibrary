package co.trance.lib.utility.guide.viper

import co.trance.lib.utility.guide.repository.IRepositoring

interface IViewing<T:IPresenting<IInteracting>> {
    val presenter: T?
    fun onDispose()

    fun onStateChanged()
    fun setViewObserver()

    fun showErrorDialog(errorMessage: String)
    fun showErrorToast(errorMessage: String)
    fun showErrorSnackBar(errorMessage: String)
}