package com.c0n4.group.controller.v1

import com.c0n4.group.controller.v1.dto.GroupBaseDTO
import com.c0n4.group.controller.v1.dto.GroupDTO
import com.c0n4.group.service.GroupService
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("v1/group")
class GroupControllerImpl constructor(private val groupService: GroupService) : GroupController {

    @Get()
    override fun getGroups(): List<GroupBaseDTO> {
        return groupService.getGroups("").map { GroupBaseDTO(it.id, it.description) }
    }

    @Get("{groupID}")
    override fun getGroup(@PathVariable groupID: String): GroupDTO {
        return GroupDTO(groupService.getGroup("", groupID))
    }

    @Post
    override fun createGroup(@Body group: GroupDTO): GroupDTO {
        return GroupDTO(groupService.createGroup("", group.toGroup()))
    }

    @Put("{groupID}")
    override fun updateGroup(@PathVariable groupID: String, @Body group: GroupDTO): GroupDTO {
        group.id = groupID
        return GroupDTO(groupService.updateGroup("", group.toGroup()))
    }

}