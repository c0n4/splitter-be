package com.c0n4.group.service.group

import com.c0n4.group.domain.Expense
import com.c0n4.group.domain.Group
import com.c0n4.group.domain.Member

interface GroupService {

    fun getGroups(userID: String): List<Group>

    fun getGroup(userID: String, idGroup: String): Group

    fun createGroup(userID: String, group: Group): Group
    fun addMember(userID: String, member: Member): Group
    fun addExpense(userID: String, expense: Expense): Group
}