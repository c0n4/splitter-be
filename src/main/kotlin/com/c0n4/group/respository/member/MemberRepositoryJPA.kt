package com.c0n4.group.respository.member

import com.c0n4.group.respository.entity.MembersEntity
import com.c0n4.group.respository.entity.MembersEntityPK
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface MemberRepositoryJPA : JpaRepository<MembersEntity, MembersEntityPK> {

    fun findByGroupId(groupId: String): List<MembersEntity>

    fun findByUserId(groupId: String): List<MembersEntity>

}