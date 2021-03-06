package com.c0n4.group.respository.entity

import com.c0n4.group.domain.Group
import javax.persistence.*

@Entity
@Table(name = "groups", schema = "public", catalog = "splitter")
open class GroupsEntity {

    @get:Id
    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: String? = null

    @get:Basic
    @get:Column(name = "name", nullable = false)
    var name: String? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "name = $name " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as GroupsEntity

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    fun toGroup(): Group {
        return Group.Builder()
            .id(id)
            .description(name)
            .build()
    }

    companion object {
        fun fromGroup(group: Group) : GroupsEntity {
            val groupsEntity = GroupsEntity()
            groupsEntity.id = group.id
            groupsEntity.name = group.description
            return groupsEntity
        }
    }

}

