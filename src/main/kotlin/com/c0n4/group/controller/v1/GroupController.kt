package com.c0n4.group.controller.v1

import com.c0n4.group.controller.v1.dto.GroupBaseDTO
import com.c0n4.group.controller.v1.dto.GroupDTO

interface GroupController {

    fun getGroups(): List<GroupBaseDTO>

    fun getGroup(groupID: String): GroupDTO

    fun createGroup(group: GroupDTO): GroupDTO

    fun updateGroup(groupID: String, group: GroupDTO): GroupDTO
}