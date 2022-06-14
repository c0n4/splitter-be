package com.c0n4.group.respository.entity

import com.c0n4.group.domain.Expense
import com.c0n4.user.domain.User
import javax.persistence.*

@Entity
@Table(name = "expenses", schema = "public", catalog = "splitter")
@IdClass(ExpensesEntityPK::class)
open class ExpensesEntity() {

    constructor(entity: Expense) : this() {
        this.id = entity.id
        this.groupId = entity.groupId
        this.amount = entity.amount
        this.description = entity.description
        this.createdAt = java.sql.Timestamp.valueOf(entity.createdAt);
        this.userId = entity.user.id
    }

    @get:Id
    @get:Column(name = "id", nullable = false)
    var id: String? = null

    @get:Id
    @get:Column(name = "group_id", nullable = false, insertable = false, updatable = false)
    var groupId: String? = null

    @get:Id
    @get:Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    var userId: String? = null

    @get:Basic
    @get:Column(name = "description", nullable = false)
    var description: String? = null

    @get:Basic
    @get:Column(name = "amount", nullable = false)
    var amount: java.math.BigDecimal? = null

    @get:Basic
    @get:Column(name = "created_at", nullable = false)
    var createdAt: java.sql.Timestamp? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "groupId = $groupId " +
                "userId = $userId " +
                "description = $description " +
                "amount = $amount " +
                "createdAt = $createdAt " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ExpensesEntity

        if (id != other.id) return false
        if (groupId != other.groupId) return false
        if (userId != other.userId) return false
        if (description != other.description) return false
        if (amount != other.amount) return false
        if (createdAt != other.createdAt) return false

        return true
    }

    fun toExpense(): Expense {
        return Expense.Builder()
            .id(id)
            .user(User.Builder().id(userId).build())
            .amount(amount)
            .description(description)
            .createdAt(createdAt?.toLocalDateTime())
            .build()
    }

}

class ExpensesEntityPK : java.io.Serializable {
    @get:Id
    @get:Column(name = "id", nullable = false)
    var id: String? = null

    @get:Id
    @get:Column(name = "group_id", nullable = false, insertable = false, updatable = false)
    var groupId: String? = null

    @get:Id
    @get:Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    var userId: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ExpensesEntityPK

        if (id != other.id) return false
        if (groupId != other.groupId) return false
        if (userId != other.userId) return false

        return true
    }

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

}
