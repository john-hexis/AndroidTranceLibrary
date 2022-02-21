package co.trance.lib.utility.guide.repository.service.annotation

/**
 * Make a DELETE Request
 * @param value A relative or absolute path, or full URL of the endpoint. This value is optional if the first parameter of the method is annotated with @Url. See base URL for details of how this is resolved against a base URL to create the full endpoint URL.
 * */
@kotlin.annotation.MustBeDocumented
@kotlin.annotation.Target(AnnotationTarget.FUNCTION)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@kotlin.Deprecated("Please don't use for this moment.")
public annotation class HttpDelete(val value: String)
