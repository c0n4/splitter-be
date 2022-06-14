package com.c0n4.group.service.member

import com.c0n4.group.domain.Member
import com.c0n4.group.respository.member.MemberRepository
import com.c0n4.user.domain.User
import com.c0n4.user.service.UserService
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val userService: UserService
) : MemberService {

    private val log = LoggerFactory.getLogger(MemberServiceImpl::class.java)

    override fun getUsers(groupId: String): List<User> {
        val members = memberRepository.findByGroupId(groupId)
            .map { userService.getUser(it.userID) }
        log.trace("getUsers(groupId: {}) -> {}", groupId, members)
        return members
    }

    override fun saveMember(member: Member) {
        log.trace("saveMember: {}", member)
        memberRepository.save(member)
    }

    override fun findByUserId(userId: String): List<Member> {
        val members = memberRepository.findByUserId(userId).map { it }
        log.trace("findByUserId(userId: {}) -> {}", userId, members)
        return members
    }
}