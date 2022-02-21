package co.trance.lib.utility.helper

import android.content.Context
import co.trance.lib.utility.guide.repository.datasource.IRequestData
import co.trance.lib.utility.guide.repository.datasource.IResponseBody
import co.trance.lib.utility.guide.repository.service.httpComponent.HttpHeader
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Single
import java.io.BufferedInputStream
import java.math.BigInteger
import java.net.URL
import java.security.cert.CertificateFactory
import java.util.*
import java.util.concurrent.locks.Lock
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager

/**
 * API service manager to handle volley transport communication.
 *
 * @param context Current main context
 * @param cerAuthInput Load certificate file to [BufferedInputStream]
 * @param certType Type of certification, by default is X.509
 * @param sslContextProtocol SSl protocol type, by default is TLS
 * */
public abstract class APIVolleyServiceManager(
    private val context: Context,
    private val cerAuthInput: BufferedInputStream,
    private val certType: CertType = CertType.X509,
    private val sslContextProtocol: SSLContextProtocol = SSLContextProtocol.TLS) {

    public enum class CertType {
        X509;

        fun getString(): String {
            return when(this) {
                X509 -> "X.509"
            }
        }
    }
    public enum class SSLContextProtocol {
        TLS;

        fun getString(): String {
            return when(this) {
                TLS -> "TLS"
            }
        }
    }

    protected val volleyQueue: RequestQueue by lazy (LazyThreadSafetyMode.SYNCHRONIZED) {
        synchronized(this) {
            Volley.newRequestQueue(
                context,
                HurlStack(null, this.getSSLSocket())
            )
        }
    }

    /**
     * Call get REST API.
     *
     * @param url Full path API endpoint
     * @param headers Http header
     * @param params Http request body with request data model
     * @return Single rx with T response body model
     * */
    protected inline fun <reified T: IResponseBody> get(url: String, headers: HttpHeader, params: IRequestData): Single<T> {
        return this.call(url, Request.Method.POST, headers.toMutableMap(), params)
    }

    /**
     * Call post REST API.
     *
     * @param url Full path API endpoint
     * @param headers Http header
     * @param params Http request body with request data model
     * @return Single rx with T response body model
     * */
    protected inline fun <reified T: IResponseBody> post(url: String, headers: HttpHeader, params: IRequestData): Single<T> {
        return this.call(url, Request.Method.POST, headers.toMutableMap(), params)
    }

    /**
     * Call put REST API.
     *
     * @param url Full path API endpoint
     * @param headers Http header
     * @param params Http request body with request data model
     * @return Single rx with T response body model
     * */
    protected inline fun <reified T: IResponseBody> put(url: String, headers: HttpHeader, params: IRequestData): Single<T> {
        return this.call(url, Request.Method.PUT, headers.toMutableMap(), params)
    }

    /**
     * Call delete REST API.
     *
     * @param url Full path API endpoint
     * @param headers Http header
     * @param params Http request body with request data model
     * @return Single rx with T response body model
     * */
    protected inline fun <reified T: IResponseBody> delete(url: String, headers: HttpHeader, params: IRequestData): Single<T> {
        return this.call(url, Request.Method.DELETE, headers.toMutableMap(), params)
    }

    private fun getSSLSocket(): SSLSocketFactory {
        val cert = CertificateFactory.getInstance(this.certType.getString())
        val cerAuth = cert.generateCertificate(cerAuthInput)
        cerAuthInput.close()
        val pubKey = cerAuth.publicKey
        val encoded = BigInteger(1 /* positive */, pubKey.encoded).toString(16)
        val trustManager = when(certType) {
            CertType.X509 -> X509PubKeyTrustManager(encoded)
        }
        val trustManagers = arrayOf<TrustManager>(trustManager)
        val sslContext = SSLContext.getInstance(sslContextProtocol.getString())
        sslContext.init(null, trustManagers, null)
        return sslContext.socketFactory
    }

    /**
     * Call REST API.
     *
     * @param url Full path API endpoint
     * @param method Http method (GET, POST, PUT, DELETE)
     * @param headers Http header (key value pairs)
     * @param params Http request body with request data model
     * @return Single rx with T response body model
     * */
    protected inline fun <reified T: IResponseBody> call(url: String, method: Int, headers:MutableMap<String, String>?, params: IRequestData): Single<T> {

        return Single.create {
            val now = Date().time.hashCode()
            val req = object : JsonObjectRequest(method, url, params.parseJson(),
                { resJson ->
                    val gson = Gson()
                    it.onSuccess(gson.fromJson(resJson.toString(), T::class.java))
                },
                { error ->
                    this.volleyQueue.cancelAll(now)
                    it.onError(error)
                }) {
                    override fun getHeaders(): MutableMap<String, String> {
                        return headers ?: mutableMapOf()
                    }
                }

            this.volleyQueue.add(req.setTag(now))
        }
    }
}