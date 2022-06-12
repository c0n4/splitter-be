package com.c0n4.group.domain

data class Member(val groupID: String, val userID: String) {
    fun toMember(): Member {
        return Member(groupID, userID)
    }
}
