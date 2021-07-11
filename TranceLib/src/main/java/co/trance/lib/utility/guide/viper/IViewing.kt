package co.trance.lib.utility.guide.viper

interface IViewing {
    val presenter: IPresenting?
    fun onDispose()

    fun onStateChanged()
    fun setViewObserver()

    fun showErrorDialog(errorMessage: String)
    fun showErrorToast(errorMessage: String)
    fun showErrorSnackBar(errorMessage: String)
}