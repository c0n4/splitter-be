package com.c0n4.security

import com.c0n4.user.domain.User
import com.c0n4.user.service.UserService
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationProvider
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink

@Singleton
class AuthenticationProviderUserPassword(private val userService: UserService) : AuthenticationProvider {
    override fun authenticate(
        httpRequest: HttpRequest<*>?,
        authenticationRequest: AuthenticationRequest<*, *>?
    ): Publisher<AuthenticationResponse> {
        return Flux.create({ emitter: FluxSink<AuthenticationResponse> ->
            var email = ""
            var password = ""
            if (authenticationRequest?.identity is String) {
                email = authenticationRequest.identity as String
            }
            if (authenticationRequest?.secret is String) {
                password = authenticationRequest.secret as String
            }
            var user: User?
            try {
                user = userService.validateUser(email, password)
            } catch (e: Exception) {
                user = null
            }
            if (user != null) {
                emitter.next(AuthenticationResponse.success(user.id))
                emitter.complete()
            } else {
                emitter.error(AuthenticationResponse.exception())
            }
        }, FluxSink.OverflowStrategy.ERROR)
    }


}