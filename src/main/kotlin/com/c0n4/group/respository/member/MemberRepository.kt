package com.c0n4.group.respository.member

import com.c0n4.group.domain.Member

interface MemberRepository {

    fun findByGroupId(groupId: String): List<Member>

    fun save(member: Member)

    fun findByUserId(userId: String): List<Member>
}