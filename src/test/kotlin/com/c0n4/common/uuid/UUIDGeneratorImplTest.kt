package com.c0n4.common.uuid

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class UUIDGeneratorImplTest {

    @Test
    fun getUUID() {
        val uuidGenerator: UUIDGenerator = UUIDGeneratorImpl()

        val uuid = uuidGenerator.getUUID()

        assertNotNull(uuid)
        assertFalse(uuid.isEmpty())
    }
}