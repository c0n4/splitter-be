package com.c0n4.group.controller.v1.dto

import com.c0n4.group.domain.Expense
import java.math.BigDecimal
import java.time.LocalDateTime

data class ExpenseDTO(
    val id: String?,
    val name: String?,
    val amount: BigDecimal?,
    val createdAt: LocalDateTime?,
) {
    constructor(expense: Expense) : this(expense.id, expense.description, expense.amount, expense.createdAt)

    fun toExpense(): Expense {
        return Expense.Builder()
            .id(id)
            .description(name)
            .amount(amount)
            .createdAt(createdAt)
            .build()
    }
}
