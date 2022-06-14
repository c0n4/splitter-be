package com.c0n4.user.repository

import com.c0n4.user.repository.entity.UsersEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UsersEntity, String> {

    fun findByUsername(username: String): Optional<UsersEntity>


    companion object {
        const val USER_ENTITY_NAME = "User"
    }
}