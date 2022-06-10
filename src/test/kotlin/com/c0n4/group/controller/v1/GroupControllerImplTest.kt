package com.c0n4.group.controller.v1

import com.c0n4.group.controller.v1.dto.GroupDTO
import com.c0n4.group.domain.Group
import com.c0n4.group.service.GroupService
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class GroupControllerImplTest {

    @InjectMocks
    lateinit var groupController: GroupControllerImpl

    @Mock
    lateinit var groupService: GroupService

    private val group = Group.Builder()
        .id("id")
        .description("description")
        .build()

    private val groupDTO = GroupDTO(
        group
    )

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getGroups() {
        Mockito.`when`(groupService.getGroups("")).thenReturn(listOf(group))
        val groupsActual = groupController.getGroups()
        assertEquals(listOf(groupDTO), groupsActual)
    }

    @Test
    fun getGroup() {
        Mockito.`when`(groupService.getGroup("", "")).thenReturn(group)
        val groupActual = groupController.getGroup("id")
        assertEquals(groupDTO, groupActual)
    }

    @Test
    fun createGroup() {
        Mockito.`when`(groupService.createGroup("", group)).thenReturn(group)
        val groupActual = groupController.createGroup(groupDTO)
        assertEquals(groupDTO, groupActual)
    }

    @Test
    fun updateGroup() {
        Mockito.`when`(groupService.updateGroup("", group)).thenReturn(group)
        val groupActual = groupController.updateGroup("id", groupDTO)
        assertEquals(groupDTO, groupActual)
    }
}