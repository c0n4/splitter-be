package com.c0n4.group.service.member

import com.c0n4.group.domain.Member
import com.c0n4.user.domain.User

interface MemberService {

    fun getUsers(groupId: String): List<User>

    fun saveMember(member: Member)

    fun findByUserId(userId: String): List<Member>
}