package com.c0n4.group.respository.entity

import com.c0n4.user.repository.entity.UsersEntity
import javax.persistence.*

@Entity
@Table(name = "members", schema = "public", catalog = "splitter")
@IdClass(MembersEntityPK::class)
open class MembersEntity {
    @get:Id
    @get:Column(name = "group_id", nullable = false, insertable = false, updatable = false)
    var groupId: String? = null

    @get:Id
    @get:Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    var userId: String? = null

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "group_id", referencedColumnName = "id")
    var refGroupsEntity: GroupsEntity? = null

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "user_id", referencedColumnName = "id")
    var refUsersEntity: UsersEntity? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "groupId = $groupId " +
                "userId = $userId " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as MembersEntity

        if (groupId != other.groupId) return false
        if (userId != other.userId) return false

        return true
    }

}

class MembersEntityPK : java.io.Serializable {
    @get:Id
    @get:Column(name = "group_id", nullable = false, insertable = false, updatable = false)
    var groupId: String? = null

    @get:Id
    @get:Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    var userId: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as MembersEntityPK

        if (groupId != other.groupId) return false
        if (userId != other.userId) return false

        return true
    }

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

}
