package co.trance.lib.utility.guide.viper.viewState

interface IViewState<T: IViewModel> {
    var status: Type
    var message: String
    var data: T?

    enum class Type {
        Successful,
        Unsuccessful,
        Error
    }
}