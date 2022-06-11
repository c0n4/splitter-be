package com.c0n4.group.controller.v1

import com.c0n4.group.controller.v1.dto.GroupBaseDTO
import com.c0n4.group.controller.v1.dto.GroupDTO
import com.c0n4.group.service.GroupService
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

    @Put("{groupID}")
    fun updateGroup(principal: Principal, @PathVariable groupID: String, @Body group: GroupDTO): GroupDTO {
        group.id = groupID
        return GroupDTO(groupService.updateGroup(principal.name, group.toGroup()))
    }

}