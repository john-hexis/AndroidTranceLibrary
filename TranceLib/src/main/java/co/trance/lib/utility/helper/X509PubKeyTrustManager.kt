package co.trance.lib.utility.helper

import java.math.BigInteger
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.security.interfaces.RSAPublicKey
import javax.net.ssl.X509TrustManager

class X509PubKeyTrustManager(private val publicKey: String) : X509TrustManager {

    @Throws(CertificateException::class)
    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
        requireNotNull(chain) { "checkServerTrusted: X509Certificate array is null" }
        require(chain.size > 0) { "checkServerTrusted: X509Certificate is empty" }

        // Hack ahead: BigInteger and toString(). We know a DER encoded Public
        // Key starts with 0x30 (ASN.1 SEQUENCE and CONSTRUCTED), so there is
        // no leading 0x00 to drop.

        for (cert in chain) {
            val pubkey = cert.publicKey as RSAPublicKey
            val encoded = BigInteger(1 /* positive */, pubkey.encoded).toString(16)

            // Pin it!
            val expected = publicKey.equals(encoded, ignoreCase = true)
            // fail if expected public key is different from our public key
            if (expected) {
                return
            }
        }
        throw CertificateException(
            "SSL Pinning Server Not trusted"
        )
    }

    override fun checkClientTrusted(xcs: Array<X509Certificate>, string: String) {
        // throw new
        // UnsupportedOperationException("checkClientTrusted: Not supported yet.");
    }

    override fun getAcceptedIssuers(): Array<X509Certificate>? {
        // throw new
        // UnsupportedOperationException("getAcceptedIssuers: Not supported yet.");
        return null
    }
}