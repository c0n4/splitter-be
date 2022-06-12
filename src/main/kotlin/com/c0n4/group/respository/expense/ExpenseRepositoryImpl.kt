package com.c0n4.group.respository.expense

import com.c0n4.group.domain.Expense
import com.c0n4.group.respository.entity.ExpensesEntity
import jakarta.inject.Singleton

@Singleton
class ExpenseRepositoryImpl(private val expenseRepositoryJPA: ExpenseRepositoryJPA) : ExpenseRepository {

    override fun getExpenses(groupId: String): List<Expense> {
        return expenseRepositoryJPA.findByGroupId(groupId).map {
            it.toExpense()
        }
    }

    override fun saveExpense(expense: Expense) {
        expenseRepositoryJPA.save(ExpensesEntity(expense))
    }
}