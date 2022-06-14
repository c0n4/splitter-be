package com.c0n4.common.error


class AlreadyExistsException(entity: String, value: String) :
    RuntimeException("$entity $value already exists")

