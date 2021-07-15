package co.trance.lib.utility.guide.repository

import co.trance.lib.utility.guide.repository.datasource.ILocalDataSource
import co.trance.lib.utility.guide.repository.datasource.IRemoteDataSource
import co.trance.lib.utility.guide.repository.service.IAPIService
import co.trance.lib.utility.guide.repository.service.IPersistenceService

interface IRepositoring

interface IRepositoringWithLocalRemote: IRepositoring  {
    val local: ILocalDataSource?
    val remote: IRemoteDataSource?
}

interface IRepositoringWithLocal: IRepositoring {
    val local: ILocalDataSource?
}

interface IRepositoringWithRemote: IRepositoring  {
    val remote: IRemoteDataSource?
}