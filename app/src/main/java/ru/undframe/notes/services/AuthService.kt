package ru.undframe.notes.services

import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import ru.undframe.notes.data.AliveToken
import ru.undframe.notes.data.User

interface AuthService {
    @Headers(value = ["Content-Type: application/json"])
    @POST("api/auth")
    fun authUser(
        @Query("login") login: String, @Query("password") password: String,
        @Query("device_id") deviceData: String,
        @Query("service_id") serviceName: String
    ): Single<User?>


    @POST("api/alivetoken")
    @Headers(value = ["Content-Type: application/json"])
    fun isAliveToken(@Query("access_token") toke: String?): Call<AliveToken?>

    @POST("api/refresh")
    @Headers(value = ["Content-Type: application/json"])
    fun refreshTokenRequest(
        @Query("id") id: Long,
        @Query("refresh_token") refreshToken: String,
        @Query("device_id") deviceId: String,
        @Query("service_id") serviceId: String
    ) : Call<User?>





}