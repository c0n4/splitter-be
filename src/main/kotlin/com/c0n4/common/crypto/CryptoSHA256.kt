package com.c0n4.common.crypto

import jakarta.inject.Singleton
import java.security.MessageDigest
import java.util.*
import javax.xml.bind.DatatypeConverter

@Singleton
class CryptoSHA256 : Crypto {

    override fun hash(input: String): String {
        val bytes = MessageDigest
            .getInstance("SHA-256")
            .digest(input.toByteArray())
        return DatatypeConverter.printHexBinary(bytes).uppercase(Locale.getDefault())
    }

}