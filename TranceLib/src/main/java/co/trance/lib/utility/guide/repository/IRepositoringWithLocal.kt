package co.trance.lib.utility.guide.repository

import co.trance.lib.utility.guide.repository.datasource.ILocalDataSource

interface IRepositoringWithLocal<T:ILocalDataSource>: IRepositoring {
    val local: T?
}