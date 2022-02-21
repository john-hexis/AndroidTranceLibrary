package co.trance.lib.utility.guide.repository.service.annotation

/**
 * Use this annotation on a service method param when you want to directly control the request body
 * of a POST/PUT request (instead of sending in as request parameters or form-style request body).
 *
 * <p>Body parameters may not be {@code null}.
 *
 * */
@kotlin.annotation.MustBeDocumented
@kotlin.annotation.Target(AnnotationTarget.PROPERTY)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@kotlin.Deprecated("Please don't use for this moment.")
public annotation class HttpBody()
