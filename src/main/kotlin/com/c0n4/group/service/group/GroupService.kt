package com.c0n4.group.service.group

import com.c0n4.group.domain.Expense
import com.c0n4.group.domain.Group
import com.c0n4.user.domain.User
import java.math.BigDecimal

interface GroupService {

    fun getGroups(userID: String): List<Group>

    fun getGroup(userID: String, idGroup: String): Group

    fun createGroup(userID: String, group: Group): Group
    fun addMember(userID: String, groupID: String, username: String): Group
    fun addExpense(userID: String, expense: Expense): Group
    fun getBalance(userID: String, groupID: String): Map<User, BigDecimal>
}