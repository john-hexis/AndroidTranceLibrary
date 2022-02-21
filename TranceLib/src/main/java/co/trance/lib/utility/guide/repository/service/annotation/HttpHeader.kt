package co.trance.lib.utility.guide.repository.service.annotation

/**
 * Replaces the header with the value of its target.
 *
 * ```
 * @GET("/")
 * Call<ResponseBody> foo(@Header("Accept-Language") String lang);
 * ```
 *
 * Header parameters may be null which will omit them from the request. Passing a List or array will result in a header for each non-null item.
 *
 * <b>Note</b> Headers do not overwrite each other. All headers with the same name will be included in the request.
 * */
@kotlin.annotation.MustBeDocumented
@kotlin.annotation.Target(AnnotationTarget.PROPERTY)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@kotlin.Deprecated("Please don't use for this moment.")
public annotation class HttpHeader(val value: String)
