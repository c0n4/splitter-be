package com.c0n4.user.domain

class User private constructor(
    val id: String,
    val name: String,
    val username: String,
    val password: String,
) {


    data class Builder(
        var id: String? = null,
        var name: String? = null,
        var username: String? = null,
        var password: String? = null,
    ) {
        fun id(id: String?) = apply { this.id = id }
        fun name(name: String?) = apply { this.name = name }
        fun username(username: String?) = apply { this.username = username }
        fun password(password: String?) = apply { this.password = password }
        fun build() = User(id ?: "", name ?: "", username ?: "", password ?: "")
    }

}