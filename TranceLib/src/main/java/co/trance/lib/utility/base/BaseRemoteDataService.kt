package co.trance.lib.utility.base

import co.trance.lib.utility.guide.repository.service.IAPIService
import co.trance.lib.utility.helper.APIRetrofitServiceManager
import okhttp3.HttpUrl


abstract class BaseRemoteDataService<T: IAPIService> {
    /**
     * Create new API service, only can create 1 instance for each service to avoid memory leaks.
     * */
    inline fun <reified T : IAPIService> createApiService(baseHttpUrl: HttpUrl?): T {
        return APIRetrofitServiceManager.instance<T>().create(T::class.java, baseHttpUrl)
    }
}