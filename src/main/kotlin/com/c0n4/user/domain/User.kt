package com.c0n4.user.domain

class User private constructor(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
) {


    data class Builder(
        var id: String? = null,
        var name: String? = null,
        var email: String? = null,
        var password: String? = null,
    ) {
        fun id(id: String?) = apply { this.id = id }
        fun name(name: String?) = apply { this.name = name }
        fun email(email: String?) = apply { this.email = email }
        fun password(password: String?) = apply { this.password = password }
        fun build() = User(id ?: "", name ?: "", email ?: "", password ?: "")
    }

}