package com.c0n4.user.service

import com.c0n4.common.crypto.Crypto
import com.c0n4.common.uuid.UUIDGenerator
import com.c0n4.group.service.group.GroupServiceImpl
import com.c0n4.user.domain.User
import com.c0n4.user.repository.UserRepository
import com.c0n4.user.repository.entity.UsersEntity
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class UserServiceImpl(
    private val crypto: Crypto,
    private val uuidGenerator: UUIDGenerator,
    private val userRepository: UserRepository
) : UserService {

    private val log = LoggerFactory.getLogger(GroupServiceImpl::class.java)

    override fun createUser(user: User): User {
        validateExistUser(user.email)
        val newUser = User.Builder()
            .email(user.email)
            .password(crypto.hash(user.password))
            .id(uuidGenerator.getUUID())
            .name(user.name)
            .build()
        userRepository.save(UsersEntity(newUser))
        log.trace("User created: $newUser")
        return newUser
    }

    private fun validateExistUser(email: String) {
        userRepository.findByEmail(email).ifPresent {
            throw IllegalArgumentException("User with email $email already exists")
        }
    }

    override fun getUser(id: String): User {
        return userRepository.findById(id).map { it.toUser() }.orElseThrow {
            IllegalArgumentException("User not found")
        }
    }

    override fun getUserByEmail(email: String): User {
        return userRepository.findByEmail(email).map { it.toUser() }.orElseThrow {
            IllegalArgumentException("User not found")
        }
    }

    override fun validateUser(email: String, password: String): User? {
        return getUserByEmail(email).takeIf {
            crypto.hash(password) == it.password
        }
    }
}