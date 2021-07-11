package co.trance.lib.utility.guide.repository.datasource

import co.trance.lib.utility.guide.repository.service.IPersistenceService

interface ILocalDataSource<S: IPersistenceService>: IDataSource<S> {
}