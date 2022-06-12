package com.c0n4.group.respository.member

import com.c0n4.group.domain.Member
import com.c0n4.group.respository.entity.MembersEntity
import jakarta.inject.Singleton

@Singleton
class MemberRepositoryImpl(private val memberRepositoryJPA: MemberRepositoryJPA) : MemberRepository {

    override fun findByGroupId(groupId: String): List<Member> {
        return memberRepositoryJPA.findByGroupId(groupId).map { it.toMember() }
    }

    override fun save(member: Member) {
        memberRepositoryJPA.save(MembersEntity(member))
    }

    override fun findByUserId(userId: String): List<Member> {
        return memberRepositoryJPA.findByUserId(userId).map { it.toMember() }
    }
}