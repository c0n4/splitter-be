package com.c0n4.user.repository.entity

import com.c0n4.user.domain.User
import javax.persistence.*

@Entity
@Table(name = "users", schema = "public", catalog = "splitter")
open class UsersEntity() {

    constructor(user: User) : this() {
        this.id = user.id
        this.name = user.name
        this.username = user.username
        this.password = user.password
    }

    @get:Id
    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: String? = null

    @get:Basic
    @get:Column(name = "username", nullable = false)
    var username: String? = null

    @get:Basic
    @get:Column(name = "name", nullable = false)
    var name: String? = null

    @get:Basic
    @get:Column(name = "password", nullable = false)
    var password: String? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "username = $username " +
                "name = $name " +
                "password = $password " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as UsersEntity

        if (id != other.id) return false
        if (username != other.username) return false
        if (name != other.name) return false
        if (password != other.password) return false

        return true
    }

    fun toUser(): User {
        return User.Builder()
            .id(id)
            .name(name)
            .username(username)
            .password(password)
            .build()
    }

}


