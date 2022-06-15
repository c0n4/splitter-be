package com.c0n4.group.domain

import com.c0n4.user.domain.User
import java.math.BigDecimal

class Group private constructor(
    var id: String,
    val description: String,
    var members: List<User>,
    var expenses: List<Expense>,
) {

    private fun getTotalAmount(): BigDecimal {
        return expenses.sumOf { it.amount }
    }

    private fun getUserExpenses(userId: String): List<Expense> {
        return expenses.filter { userId == it.user.id }
    }

    private fun getUserAmount(userId: String?): BigDecimal {
        if (userId == null) return BigDecimal.ZERO
        return getUserExpenses(userId).sumOf { it.amount }
    }

    fun getBalance(): Map<User, BigDecimal> {
        val totalAmount = getTotalAmount() / BigDecimal(members.size)
        return members.associateWith { totalAmount - getUserAmount(it.id) }
    }


    data class Builder(
        var id: String? = null,
        var description: String? = null,
        var members: List<User>? = null,
        var expenses: List<Expense>? = null,
    ) {

        fun id(id: String?) = apply { this.id = id }
        fun description(description: String?) = apply { this.description = description }
        fun members(members: List<User>) = apply { this.members = members }
        fun expenses(expenses: List<Expense>) = apply { this.expenses = expenses }

        fun build() = Group(id ?: "", description ?: "", members ?: emptyList(), expenses ?: emptyList())
    }
}