package com.c0n4.user.repository.entity

import com.c0n4.group.respository.entity.ExpensesEntity
import com.c0n4.group.respository.entity.MembersEntity
import com.c0n4.user.domain.User
import javax.persistence.*

@Entity
@Table(name = "users", schema = "public", catalog = "splitter")
open class UsersEntity {

    constructor(user: User) {
        this.id = user.id
        this.username = user.name
        this.email = user.email
        this.password = user.password
    }

    @get:Id
    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: String? = null

    @get:Basic
    @get:Column(name = "email", nullable = false)
    var email: String? = null

    @get:Basic
    @get:Column(name = "username", nullable = false)
    var username: String? = null

    @get:Basic
    @get:Column(name = "password", nullable = false)
    var password: String? = null

    @get:OneToMany(mappedBy = "refUsersEntity")
    var refExpensesEntities: List<ExpensesEntity>? = null

    @get:OneToMany(mappedBy = "refUsersEntity")
    var refMembersEntities: List<MembersEntity>? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "email = $email " +
                "username = $username " +
                "password = $password " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as UsersEntity

        if (id != other.id) return false
        if (email != other.email) return false
        if (username != other.username) return false
        if (password != other.password) return false

        return true
    }

}


