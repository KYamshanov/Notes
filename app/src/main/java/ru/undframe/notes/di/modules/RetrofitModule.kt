package ru.undframe.notes.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.undframe.notes.BuildConfig
import ru.undframe.notes.data.User
import ru.undframe.notes.di.scopes.BaseSingletonScope
import ru.undframe.notes.services.AuthService
import ru.undframe.notes.services.IdentifierDeviceService
import ru.undframe.notes.services.UserService
import javax.inject.Qualifier


@Module
class RetrofitModule {

    @BaseSingletonScope
    @Provides
    fun authService(
        identifierDeviceService: IdentifierDeviceService,
        userService: UserService
    ): AuthService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE


        val default = Retrofit.Builder()
            .baseUrl("http://undframe.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(interceptor).build()
            )
            .build().create(AuthService::class.java)


        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val currentUser = userService.currentUser

                val request = chain.request()

                val urlBuilder = request.url().newBuilder()


                if (currentUser.isAuthorization()) {
                    if (checkToken(default, currentUser.accessToken!!)) {
                        urlBuilder.addQueryParameter(
                            "access_token",
                            currentUser.accessToken
                        )
                    } else {
                        refreshTokenRequest(default, currentUser, identifierDeviceService).execute().apply {
                            processingAliveToken(userService, urlBuilder, currentUser)
                        }
                    }

                }
                val requestBuilder = request.newBuilder()
                requestBuilder.url(urlBuilder.build())

                chain.proceed(requestBuilder.build())
            }
            .build()

        return Retrofit.Builder()
            .baseUrl("http://undframe.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build().create(AuthService::class.java)
    }

    private fun retrofit2.Response<User?>.processingAliveToken(
        userService: UserService,
        urlBuilder: HttpUrl.Builder,
        currentUser: User
    ) {
        if (this.isSuccessful) {
            this.body()?.let { user ->
                userService.updateUser(user)
                urlBuilder.addQueryParameter(
                    "access_token",
                    currentUser.accessToken
                )
            }
        } else
            currentUser.fillData(User.getInstance())
    }

    private fun refreshTokenRequest(
        default: AuthService,
        currentUser: User,
        identifierDeviceService: IdentifierDeviceService
    ) = default.refreshTokenRequest(
        currentUser.id,
        currentUser.refreshToken!!,
        identifierDeviceService.getDeviceId()!!,
        "NEEDLE"
    )

    fun checkToken(authService: AuthService, token: String): Boolean {
        authService.isAliveToken(token)
            .execute().apply {
                if (this.isSuccessful) {
                    this.body()?.let {
                        return true;
                    }
                }
            }
        return false
    }

}