package com.c0n4.common.error


class NotFoundException(entity: String, value: String) :
    RuntimeException("$entity not found: $value")

