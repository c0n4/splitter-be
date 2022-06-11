package com.c0n4.user.repository

import com.c0n4.user.repository.entity.UsersEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UsersEntity, String> {

    fun findByEmail(email: String): Optional<UsersEntity>

}