package com.example.usermanagement.features.get_users.data.models

import com.example.usermanagement.features.get_users.domain.entities.UserEntity
import com.google.gson.Gson
import java.util.Objects
import java.util.UUID

class UserModel(uuid: UUID, lastName: String, firstName: String)
    : UserEntity(uuid, lastName, firstName) {
    companion object {
        fun fromJSON(jsonString: String): UserModel {
            return Gson().fromJson(jsonString, UserModel::class.java)
        }
    }

    override fun equals(other: Any?): Boolean {
        return if (other is UserModel) {
            other.uuid == uuid && other.lastName == lastName && other.firstName == firstName
        } else false
    }

    override fun hashCode(): Int {
        return Objects.hash(uuid, lastName, firstName)
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}