package co.trance.lib.utility.guide.repository.datasource

import co.trance.lib.utility.guide.repository.service.IAPIService

interface IRemoteDataSource<S: IAPIService>: IDataSource<S> {
}