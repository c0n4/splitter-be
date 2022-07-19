package com.c0n4.group.domain

import com.c0n4.user.domain.User
import java.math.BigDecimal
import java.time.ZonedDateTime

class Expense private constructor(
    var id: String,
    val groupId: String,
    val user: User,
    val description: String,
    val amount: BigDecimal,
    val createdAt: ZonedDateTime,
) {


    data class Builder(
        var id: String? = null,
        var groupId: String? = null,
        var user: User? = null,
        var description: String? = null,
        var amount: BigDecimal? = null,
        var createdAt: ZonedDateTime? = null,
    ) {

        fun id(id: String?) = apply { this.id = id }

        fun groupId(groupId: String?) = apply { this.groupId = groupId }

        fun user(user: User?) = apply { this.user = user }

        fun description(description: String?) = apply { this.description = description }

        fun amount(amount: BigDecimal?) = apply { this.amount = amount }

        fun createdAt(createdAt: ZonedDateTime?) = apply { this.createdAt = createdAt }

        fun build(): Expense {
            return Expense(
                id ?: "",
                groupId ?: "",
                user ?: User.Builder().build(),
                description ?: "",
                amount ?: BigDecimal.ZERO,
                createdAt ?: ZonedDateTime.now(),
            )
        }

    }
}

fun List<Expense>.getTotalAmount(): BigDecimal = sumOf { it.amount }