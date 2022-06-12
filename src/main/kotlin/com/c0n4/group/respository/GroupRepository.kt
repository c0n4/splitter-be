package com.c0n4.group.respository

import com.c0n4.group.respository.entity.GroupsEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface GroupRepository : JpaRepository<GroupsEntity, String> {

}