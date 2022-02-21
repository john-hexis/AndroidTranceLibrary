package co.trance.lib.utility.guide.repository.service.httpComponent

data class HttpHeader(
    val accept: ContentType,
    val contentType: ContentType
) {
    private lateinit var additional: MutableMap<String, String>
    enum class ContentType {
        ApplicationXml,
        PlainText,
        ApplicationJson,
        ApplicationFormUrlEncoded,
        ApplicationOctetStream;

        public fun getValue(): String {
            return when(this) {
                ApplicationXml -> "application/xml"
                ApplicationJson -> "application/json"
                ApplicationOctetStream -> "application/octet-stream"
                ApplicationFormUrlEncoded -> "application/x-www-form-urlencoded"
                else -> "text/plain"
            }
        }
    }

    fun addHeader(key: String, value: String): HttpHeader {
        additional[key] = value
        return this
    }

    fun toMutableMap(): MutableMap<String, String> {
        val result = mutableMapOf<String, String>(
            "Accept" to accept.getValue(),
            "Content-Type" to contentType.getValue()
        )

        result.putAll(additional)
        return result
    }
}
