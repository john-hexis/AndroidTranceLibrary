package co.trance.lib.utility.guide.repository

data class CommonResponse<T: IResponseData> (
    var status: Int,
    var data: T?,
    var message: String?
)