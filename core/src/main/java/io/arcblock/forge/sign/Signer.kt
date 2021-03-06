package io.arcblock.forge.sign

import com.google.crypto.tink.subtle.Ed25519Sign
import com.google.crypto.tink.subtle.Ed25519Verify
import io.arcblock.forge.did.KeyType
import io.arcblock.forge.did.KeyType.ED25519
import io.arcblock.forge.did.KeyType.SECP256K1
import io.arcblock.forge.encodeToDER
import org.bitcoinj.core.ECKey.CURVE
import org.spongycastle.asn1.ASN1InputStream
import org.spongycastle.asn1.ASN1Integer
import org.spongycastle.asn1.DLSequence
import org.spongycastle.crypto.params.ECPublicKeyParameters
import org.spongycastle.crypto.signers.ECDSASigner
import org.web3j.crypto.ECDSASignature
import org.web3j.crypto.ECKeyPair
import java.io.IOException

/**
 *  Singer help you to Sign any binary ,such as a Transaction.
 *  and help you to verify if the signature is correct
 *
 **/
object Signer {

  /**
   * signature a binary
   * @param keyType privateKey Type ED25519 or SECP256K1
   * @param content what you want to signature
   * @param sk your wallet private key
   * @return signature out put
   *
   */
  fun sign(keyType: KeyType, content: ByteArray, sk: ByteArray): ByteArray {
    return when (keyType) {
      ED25519 -> {
        Ed25519Sign(sk.sliceArray(0..31)).sign(content)
      }
      SECP256K1 -> {
        ECKeyPair.create(sk).sign(content).encodeToDER()
      }
    }
  }

  /**
   * verify a signature is correct
   * @param keyType privateKey Type ED25519 or SECP256K1
   * @param content what you want to signature
   * @param pk your wallet public key
   * @param signature signature binary
   * @return is Correct
   */
  fun verify(keyType: KeyType, content: ByteArray, pk: ByteArray, signature: ByteArray): Boolean {
    try {
      return when (keyType) {
        ED25519 -> {
          Ed25519Verify(pk).verify(signature, content)
          true
        }
        SECP256K1 -> {
          return verify(content, signature, pk)
        }
      }
    } catch (e: Exception) {
      return false
    }
  }

  /**
   * SECP256K1 verify
   */
  private fun verify(data: ByteArray, signature: ByteArray, pk: ByteArray): Boolean {
    val signer = ECDSASigner()
    val params = ECPublicKeyParameters(CURVE.curve.decodePoint(pk), CURVE)
    signer.init(false, params)
    val sig = decodeFromDER(signature)
    return signer.verifySignature(data, sig.r, sig.s)
  }

  private fun decodeFromDER(bytes: ByteArray): ECDSASignature {
    var decoder: ASN1InputStream? = null
    try {
      decoder = ASN1InputStream(bytes)
      val seq = decoder.readObject() as DLSequence ?: throw RuntimeException("Reached past end of ASN.1 stream.")
      val r: ASN1Integer
      val s: ASN1Integer
      try {
        r = seq.getObjectAt(0) as ASN1Integer
        s = seq.getObjectAt(1) as ASN1Integer
      } catch (e: ClassCastException) {
        throw IllegalArgumentException(e)
      }
      // OpenSSL deviates from the DER spec by interpreting these values as unsigned, though they should not be
      // Thus, we always use the positive versions. See: http://r6.ca/blog/20111119T211504Z.html
      return ECDSASignature(r.positiveValue, s.positiveValue)
    } catch (e: IOException) {
      throw RuntimeException(e)
    } finally {
      if (decoder != null) try {
        decoder.close()
      } catch (x: IOException) {
      }
    }
  }
}
