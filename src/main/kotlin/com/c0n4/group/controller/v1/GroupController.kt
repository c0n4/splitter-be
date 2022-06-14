package com.c0n4.group.controller.v1

import com.c0n4.group.controller.v1.dto.*
import com.c0n4.group.service.group.GroupService
import com.c0n4.user.controller.v1.dto.UserDTO
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import java.security.Principal

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("v1/groups")
class GroupController constructor(private val groupService: GroupService) {

    @Get()
    fun getGroups(principal: Principal): List<GroupBaseDTO> {
        return groupService.getGroups(principal.name).map { GroupBaseDTO(it.id, it.description) }
    }

    @Get("{groupID}")
    fun getGroup(principal: Principal, @PathVariable groupID: String): GroupDTO {
        return GroupDTO(groupService.getGroup(principal.name, groupID))
    }

    @Post
    fun createGroup(principal: Principal, @Body group: GroupDTO): GroupDTO {
        return GroupDTO(groupService.createGroup(principal.name, group.toGroup()))
    }

    @Post("{groupID}/members")
    fun addMember(
        principal: Principal,
        @PathVariable groupID: String,
        @Body createMemberDTO: CreateMemberDTO
    ): GroupDTO {
        return GroupDTO(groupService.addMember(principal.name, groupID, createMemberDTO.username))
    }

    @Post("{groupID}/expenses")
    fun addExpense(principal: Principal, @PathVariable groupID: String, @Body expenseDTO: ExpenseDTO): GroupDTO {
        return GroupDTO(groupService.addExpense(principal.name, expenseDTO.toExpense(groupID)))
    }

    @Get("{groupID}/balance")
    fun getBalance(principal: Principal, @PathVariable groupID: String): List<BalanceDTO> {
        return groupService.getBalance(principal.name, groupID).map { BalanceDTO(UserDTO(it.key), it.value) }
    }

    @Get("{groupID}/debts")
    fun getDebts(principal: Principal, @PathVariable groupID: String): List<BalanceDTO> {
        return groupService.getBalance(principal.name, groupID).map { BalanceDTO(UserDTO(it.key), it.value) }
    }

}