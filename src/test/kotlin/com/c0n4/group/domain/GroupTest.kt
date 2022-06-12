package com.c0n4.group.domain

import com.c0n4.user.domain.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class GroupTest {

    private val user1 = User.Builder()
        .id("1")
        .build()
    private val user2 = User.Builder()
        .id("2")
        .build()

    @Test
    fun `should calculate debit with expenses`() {
        val group = getGroupWithExpenses()

        val userDebts = group.getBalance()

        assertEquals(2, userDebts.size)
        assertEquals(BigDecimal.valueOf(10), userDebts[user1])
        assertEquals(BigDecimal.valueOf(-10), userDebts[user2])
    }


    @Test
    fun `should calculate wih expenses`() {
        val group = getGroupWithoutExpense()

        val userDebts = group.getBalance()

        assertEquals(2, userDebts.size)
        assertEquals(BigDecimal.valueOf(0), userDebts[user1])
        assertEquals(BigDecimal.valueOf(0), userDebts[user2])
    }

    @Test
    fun `should build Group`() {
        val user = User.Builder().build()
        val expense = Expense.Builder().build()
        val group = Group.Builder()
            .id("1")
            .description("test")
            .members(
                listOf(
                    user,
                )
            )
            .expenses(
                listOf(
                    expense
                )
            )
            .build()
        assertEquals("1", group.id)
        assertEquals("test", group.description)
        assertEquals(user, group.members[0])
        assertEquals(expense, group.expenses[0])
    }


    private fun getGroupWithExpenses(): Group {
        return Group.Builder()
            .id("1")
            .description("test")
            .members(
                listOf(
                    user1,
                    user2
                )
            )
            .expenses(
                listOf(
                    Expense.Builder()
                        .id("1")
                        .description("expense1")
                        .amount(BigDecimal.valueOf(5))
                        .user(user1)
                        .build(),
                    Expense.Builder()
                        .id("1")
                        .description("expense2")
                        .amount(BigDecimal.valueOf(10))
                        .user(user1)
                        .build(),
                    Expense.Builder()
                        .id("1")
                        .description("expense2")
                        .amount(BigDecimal.valueOf(5))
                        .user(user2)
                        .build(),
                    Expense.Builder()
                        .id("1")
                        .description("expense3")
                        .amount(BigDecimal.valueOf(10))
                        .user(user2)
                        .build(),
                    Expense.Builder()
                        .id("1")
                        .description("expense4")
                        .amount(BigDecimal.valueOf(20))
                        .user(user1)
                        .build()
                )
            )
            .build()
    }

    fun getGroupWithoutExpense(): Group {
        return Group.Builder()
            .id("1")
            .description("test")
            .members(
                listOf(
                    user1,
                    user2
                )
            )
            .build()
    }
}