package com.c0n4.common.uuid

import jakarta.inject.Singleton
import java.util.*

@Singleton
class UUIDGeneratorImpl : UUIDGenerator {
    override fun getUUID(): String {
        return UUID.randomUUID().toString()
    }

}