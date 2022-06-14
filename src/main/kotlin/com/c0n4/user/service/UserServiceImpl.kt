package com.c0n4.user.service

import com.c0n4.common.crypto.Crypto
import com.c0n4.common.error.AlreadyExistsException
import com.c0n4.common.error.NotFoundException
import com.c0n4.common.uuid.UUIDGenerator
import com.c0n4.group.service.group.GroupServiceImpl
import com.c0n4.user.domain.User
import com.c0n4.user.repository.UserRepository
import com.c0n4.user.repository.UserRepository.Companion.USER_ENTITY_NAME
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
        validateExistUser(user.username)
        val newUser = User.Builder()
            .username(user.username)
            .password(crypto.hash(user.password))
            .id(uuidGenerator.getUUID())
            .name(user.name)
            .build()
        userRepository.save(UsersEntity(newUser))
        log.trace("User created: $newUser")
        return newUser
    }

    private fun validateExistUser(username: String) {
        userRepository.findByUsername(username).ifPresent {
            throw AlreadyExistsException(USER_ENTITY_NAME, username)
        }
    }

    override fun getUser(id: String): User {
        return userRepository.findById(id).map { it.toUser() }.orElseThrow {
            NotFoundException(USER_ENTITY_NAME, id)
        }
    }

    override fun getUserByUsername(username: String): User {
        return userRepository.findByUsername(username).map { it.toUser() }.orElseThrow {
            NotFoundException(USER_ENTITY_NAME, username)
        }
    }

    override fun validateUser(username: String, password: String): User? {
        return getUserByUsername(username).takeIf {
            crypto.hash(password) == it.password
        }
    }
}