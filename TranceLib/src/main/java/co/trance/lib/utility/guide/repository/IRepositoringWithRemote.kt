package co.trance.lib.utility.guide.repository

import co.trance.lib.utility.guide.repository.datasource.IRemoteDataSource

interface IRepositoringWithRemote<T:IRemoteDataSource>: IRepositoring {
    val remote: T?
}