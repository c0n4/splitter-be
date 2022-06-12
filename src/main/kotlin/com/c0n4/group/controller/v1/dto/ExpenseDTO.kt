package com.c0n4.group.controller.v1.dto

import com.c0n4.group.domain.Expense
import com.c0n4.user.domain.User
import io.micronaut.core.convert.format.Format
import java.math.BigDecimal
import java.time.LocalDate

data class ExpenseDTO(
    val id: String?,
    val name: String,
    val owner: String,
    val amount: BigDecimal,
    @Format("yyyy-MM-dd")
    val createdAt: LocalDate,
) {
    constructor(expense: Expense) : this(
        expense.id,
        expense.description,
        expense.user.id,
        expense.amount,
        expense.createdAt
    )

    fun toExpense(): Expense {
        return Expense.Builder()
            .id(id)
            .user(User.Builder().id(owner).build())
            .description(name)
            .amount(amount)
            .createdAt(createdAt)
            .build()
    }

    fun toExpense(groupId: String): Expense {
        return Expense.Builder()
            .id(id)
            .user(User.Builder().id(owner).build())
            .groupId(groupId)
            .description(name)
            .amount(amount)
            .createdAt(createdAt)
            .build()
    }
}
