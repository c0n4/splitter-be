package com.c0n4.user.repository

import com.c0n4.user.domain.User
import com.c0n4.user.repository.entity.UsersEntity
import jakarta.inject.Singleton
import javax.persistence.EntityManager

@Singleton
class UserRepositoryImpl(private val entityManager: EntityManager) : UserRepository {


    override fun createUser(user: User) {
        entityManager.persist(UsersEntity(user))
    }

    override fun getUser(id: String): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}