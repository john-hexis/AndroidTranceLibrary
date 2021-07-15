package co.trance.lib.utility.base

import co.trance.lib.utility.guide.repository.service.IAPIService
import co.trance.lib.utility.helper.APIServiceManager
import okhttp3.HttpUrl


abstract class BaseRemoteDataService<T: IAPIService> {
//    protected abstract val apiService: T
    /**
     * Create new API service, only can create 1 instance for each service to avoid memory leaks.
     * */
    inline fun <reified T : IAPIService> createApiService(baseHttpUrl: HttpUrl?): T {
        return APIServiceManager.instance<T>().create(T::class.java, baseHttpUrl)
    }
}