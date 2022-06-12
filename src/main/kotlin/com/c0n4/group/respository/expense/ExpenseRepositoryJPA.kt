package com.c0n4.group.respository.expense

import com.c0n4.group.respository.entity.ExpensesEntity
import com.c0n4.group.respository.entity.ExpensesEntityPK
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface ExpenseRepositoryJPA : JpaRepository<ExpensesEntity, ExpensesEntityPK> {

    fun findByGroupId(groupId: String): List<ExpensesEntity>

}