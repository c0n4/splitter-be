package com.c0n4.group.service.group

import com.c0n4.common.error.NotFoundException
import com.c0n4.common.error.UnauthorizedException
import com.c0n4.common.uuid.UUIDGenerator
import com.c0n4.group.domain.Expense
import com.c0n4.group.domain.Group
import com.c0n4.group.domain.Member
import com.c0n4.group.respository.group.GroupRepository
import com.c0n4.group.respository.group.GroupRepository.Companion.GROUP
import com.c0n4.group.service.expense.ExpenseService
import com.c0n4.group.service.member.MemberService
import com.c0n4.user.domain.User
import com.c0n4.user.service.UserService
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import java.math.BigDecimal

@Singleton
class GroupServiceImpl(
    private val uuidGenerator: UUIDGenerator,
    private val groupRepository: GroupRepository,
    private val memberService: MemberService,
    private val expenseService: ExpenseService,
    private val userService: UserService
) : GroupService {

    private val log = LoggerFactory.getLogger(GroupServiceImpl::class.java)

    override fun getGroups(userID: String): List<Group> {
        val members = memberService.findByUserId(userID)
        if (members.isEmpty()) {
            return emptyList()
        }
        val groups = members.map { this.getGroup(it.groupID) }
        log.trace("getGroups(userID: {}) -> {}", userID, groups)
        return groups
    }

    override fun getGroup(userID: String, idGroup: String): Group {
        val group = getGroupValidatingOwner(userID, idGroup)
        log.trace("getGroup(userID: {}, idGroup: {}) -> {}", userID, idGroup, group)
        return group
    }

    private fun getGroupValidatingOwner(userID: String, idGroup: String): Group {
        val group = groupRepository.findById(idGroup).orElseThrow {
            NotFoundException(GROUP, idGroup)
        }
        validateUserInGroup(userID, group)
        group.members = memberService.getUsers(idGroup)
        group.expenses = expenseService.getExpenses(idGroup)
        return group
    }


    override fun createGroup(userID: String, group: Group): Group {
        group.id = uuidGenerator.getUUID()
        groupRepository.saveGroup(group)
        memberService.saveMember(Member(group.id, userID))
        log.trace("createGroup(userID: {}, group: {}) -> {}", userID, group, group)
        return group
    }

    override fun addMember(userID: String, member: Member): Group {
        val group = getGroupValidatingOwner(userID, member.groupID)
        val user = userService.getUser(member.userID)
        memberService.saveMember(member)
        group.members = getUpdatedMembers(group, user)
        log.trace("addMember(userID: {}, member: {}) -> {}", userID, member, group)
        return group
    }

    private fun getUpdatedMembers(group: Group, user: User): List<User> {
        val members = mutableListOf(user)
        members.addAll(group.members)
        return members
    }

    override fun addExpense(userID: String, expense: Expense): Group {
        val group = getGroupValidatingOwner(userID, expense.groupId)
        expense.id = uuidGenerator.getUUID()
        expenseService.saveExpense(expense)
        group.expenses = getUpdatedExpenses(group, expense)
        log.trace("addExpense(userID: {}, expense: {}) -> {}", userID, expense, group)
        return group
    }

    override fun getBalance(userID: String, groupID: String): Map<User, BigDecimal> {
        val group = getGroupValidatingOwner(userID, groupID)
        return group.getBalance()
    }

    private fun getUpdatedExpenses(group: Group, expense: Expense): List<Expense> {
        val expenses = mutableListOf(expense)
        expenses.addAll(group.expenses)
        return expenses
    }

    private fun validateUserInGroup(userID: String, group: Group) {
        if (!group.members.none { it.id == userID }) {
            throw UnauthorizedException()
        }
    }

    private fun getGroup(idGroup: String): Group {
        return groupRepository.findById(idGroup).orElseThrow {
            NotFoundException(GROUP, idGroup)
        }
    }
}