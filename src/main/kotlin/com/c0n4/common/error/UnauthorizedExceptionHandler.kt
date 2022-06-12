package com.c0n4.common.error

import io.micronaut.context.annotation.Requirements
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.http.server.exceptions.response.ErrorContext
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor
import jakarta.inject.Singleton


@Produces
@Singleton
@Requirements(
    Requires(classes = [UnauthorizedException::class, ExceptionHandler::class])
)
class UnauthorizedExceptionHandler(private val errorResponseProcessor: ErrorResponseProcessor<Any>) :
    ExceptionHandler<UnauthorizedException, HttpResponse<*>> {

    override fun handle(request: HttpRequest<*>, exception: UnauthorizedException): HttpResponse<*> {
        return errorResponseProcessor.processResponse(
            ErrorContext.builder(request)
                .cause(exception)
                .errorMessage("Unauthorized to access this resource")
                .build(), HttpResponse.unauthorized<Any>()
        ) //
    }
}

