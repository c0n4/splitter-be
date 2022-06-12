package com.c0n4.group.service.expense

import com.c0n4.group.domain.Expense

interface ExpenseService {

    fun getExpenses(groupId: String): List<Expense>

    fun saveExpense(expense: Expense)
}