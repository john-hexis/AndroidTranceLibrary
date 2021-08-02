package co.trance.lib.utility.guide.repository.datasource

data class CommonResponse<T: IResponseData> (
    var status: Int,
    var data: T?,
    var message: String?
)