package ru.undframe.notes.services

import io.reactivex.rxjava3.core.Single
import retrofit2.http.POST
import retrofit2.http.Query
import ru.undframe.notes.data.User

interface AuthService {
    @POST("api/auth")
    fun authUser(
        @Query("login") login: String, @Query("password") password: String,
        @Query("device_id") deviceData: String,
        @Query("service_id") serviceName: String
    ): Single<User?>
}