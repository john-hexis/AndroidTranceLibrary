package co.trance.lib.utility.guide.repository.datasource

import co.trance.lib.utility.guide.repository.service.IService

interface IDataSource<S: IService> {
    val service: S
}