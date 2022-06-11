package com.c0n4.user.service

import com.c0n4.common.crypto.Crypto
import com.c0n4.common.uuid.UUIDGenerator
import com.c0n4.user.domain.User
import com.c0n4.user.repository.UserRepository
import com.c0n4.user.repository.entity.UsersEntity
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class UserServiceImpl(
    private val crypto: Crypto,
    private val uuidGenerator: UUIDGenerator
) :
    UserService {

    @Inject
    lateinit var userRepository: UserRepository

    override fun createUser(user: User): User {
        validateExistUser(user.email)
        val newUser = User.Builder()
            .email(user.email)
            .password(crypto.hash(user.password))
            .id(uuidGenerator.getUUID())
            .name(user.name)
            .build()
        userRepository.save(UsersEntity(newUser))
        return newUser
    }

    private fun validateExistUser(email: String) {
        userRepository.findByEmail(email).ifPresent {
            throw IllegalArgumentException("User with email ${email} already exists")
        }
    }

    override fun getUser(id: String): User {
        return userRepository.findById(id).map { it.toUser() }.orElseThrow {
            IllegalArgumentException("User not found")
        }
    }

    fun getUserByEmail(email: String): User {
        return userRepository.findByEmail(email).map { it.toUser() }.orElseThrow {
            IllegalArgumentException("User not found")
        }
    }
}