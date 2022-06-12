package com.c0n4.common.error


class NotFoundException(private val entity: String, private val value: String) :
    RuntimeException("$entity not found: $value")

