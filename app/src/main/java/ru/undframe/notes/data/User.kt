package ru.undframe.notes.data

import com.google.gson.annotations.SerializedName
import org.json.JSONException
import org.json.JSONObject
import ru.undframe.notes.utils.ResponseStatus

data class User(
    var id: Long,
    var username: String,
    var email: String,
    @SerializedName("auth_status") var authStatus: Int,
    @SerializedName("access_token") var accessToken: String?,
    @SerializedName("refresh_token") var refreshToken: String?
) {




    fun fillData(user: User) {
        id = user.id
        username = user.username
        email = user.email
        authStatus = user.authStatus
        accessToken = user.accessToken
        refreshToken = user.refreshToken
    }

    fun isAuthorization(): Boolean {
        return accessToken!=null
    }

    companion object {
        fun deserialize(jsonObject: JSONObject): User {
            var user: User = getInstance()
            try {
                val authStatus =
                    if (jsonObject.has("auth_status")) jsonObject.getInt("auth_status") else ResponseStatus.ERROR
                user.authStatus = authStatus
                if (authStatus == ResponseStatus.SUCCESSFUL_AUTHORIZATION) {
                    user.id = jsonObject.getLong("id")
                    user.username = (jsonObject.getString("username"))
                    user.email = (jsonObject.getString("email"))
                    user.accessToken = (jsonObject.getString("access_token"))
                    user.refreshToken = (jsonObject.getString("refresh_token"))
                    user.authStatus = (authStatus)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                user = getInstance()
            }
            return user
        }

        @JvmStatic
        fun getInstance(): User {
            val user: User = User(-1, "null", "null",  -1, null, null)
            user.authStatus = ResponseStatus.NOT_AUTHORIZED
            return user
        }
    }

}