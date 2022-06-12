package com.c0n4.group.service.expense

import com.c0n4.group.domain.Expense
import com.c0n4.group.respository.expense.ExpenseRepository
import jakarta.inject.Singleton

@Singleton
class ExpenseServiceImpl(private val expenseRepository: ExpenseRepository) : ExpenseService {

    override fun getExpenses(groupId: String): List<Expense> {
        return expenseRepository.getExpenses(groupId)
    }

    override fun saveExpense(expense: Expense) {
        return expenseRepository.saveExpense(expense)
    }
}