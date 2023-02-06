package com.example.usermanagement.features.get_users.domain.entities
import java.util.*

open class UserEntity (val uuid: UUID, val lastName: String, val firstName: String) {
    override fun equals(other: Any?): Boolean {
        return if (other is UserEntity) {
            other.uuid == uuid
        } else false
    }

    override fun hashCode() = Objects.hash(uuid)
}