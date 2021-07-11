package co.trance.lib.utility.guide.repository

import co.trance.lib.utility.guide.repository.datasource.ILocalDataSource
import co.trance.lib.utility.guide.repository.datasource.IRemoteDataSource
import co.trance.lib.utility.guide.repository.service.IAPIService
import co.trance.lib.utility.guide.repository.service.IPersistenceService

interface IRepositoring

interface IRepositoringWithLocalRemote<L: ILocalDataSource<IPersistenceService>, R: IRemoteDataSource<IAPIService>>: IRepositoring  {
    val local: L?
    val remote: R?
}

interface IRepositoringWithLocal<L: ILocalDataSource<IPersistenceService>>: IRepositoring {
    val local: L?
}

interface IRepositoringWithRemote<R: IRemoteDataSource<IAPIService>>: IRepositoring  {
    val remote: R?
}