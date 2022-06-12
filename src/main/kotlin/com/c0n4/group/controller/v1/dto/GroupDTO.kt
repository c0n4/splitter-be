package com.c0n4.group.controller.v1.dto

import com.c0n4.group.domain.Group
import com.c0n4.user.controller.v1.dto.UserDTO

data class GroupDTO(
    var id: String?,
    val description: String?,
    val members: List<UserDTO>?,
    val expenses: List<ExpenseDTO>?
) {
    fun toGroup(): Group {
        return Group.Builder()
            .id(id)
            .description(description)
            .members(members?.map { it.toUser() } ?: listOf())
            .expenses(expenses?.map { it.toExpense() } ?: listOf())
            .build()
    }

    constructor(group: Group) : this(
        group.id,
        group.description,
        group.members.map { UserDTO(it) },
        group.expenses.map { ExpenseDTO(it) })
}


