package com.c0n4.group.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.ZonedDateTime

internal class ExpenseTest {

    @Test
    fun buildExpense() {
        val now = ZonedDateTime.now()
        val amount = BigDecimal.valueOf(100.0)

        val expense = Expense.Builder()
            .id("1")
            .amount(amount)
            .description("description")
            .createdAt(now)
            .build()


        assertEquals("1", expense.id)
        assertEquals("description", expense.description)
        assertEquals(amount, expense.amount)
        assertEquals(now, expense.createdAt)
    }
}