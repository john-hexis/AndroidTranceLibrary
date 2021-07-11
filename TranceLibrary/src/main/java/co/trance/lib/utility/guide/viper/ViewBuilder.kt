package co.trance.lib.utility.guide.viper

abstract class ViewBuilder<V: IViewing> {
    abstract fun build(): V
}