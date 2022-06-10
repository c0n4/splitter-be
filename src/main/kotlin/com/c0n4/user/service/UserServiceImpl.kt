package com.c0n4.user.service

import com.c0n4.common.crypto.Crypto
import com.c0n4.common.uuid.UUIDGenerator
import com.c0n4.user.domain.User
import com.c0n4.user.repository.UserRepository
import jakarta.inject.Singleton

@Singleton
class UserServiceImpl(
    private val crypto: Crypto,
    private val uuidGenerator: UUIDGenerator,
    private val userRepository: UserRepository
) :
    UserService {

    override fun createUser(user: User): User {
        val newUser = User.Builder()
            .email(user.email)
            .password(crypto.hash(user.password))
            .id(uuidGenerator.getUUID())
            .name(user.name)
            .build()
        userRepository.createUser(newUser)
        return newUser
    }

    override fun getUser(id: String): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}