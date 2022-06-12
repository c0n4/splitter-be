package com.c0n4.group.respository.expense

import com.c0n4.group.domain.Expense

interface ExpenseRepository {

    fun getExpenses(groupId: String): List<Expense>

    fun saveExpense(expense: Expense)
}