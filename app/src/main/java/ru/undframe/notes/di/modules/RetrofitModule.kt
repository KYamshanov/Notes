package ru.undframe.notes.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.undframe.notes.BuildConfig
import ru.undframe.notes.di.scopes.BaseSingletonScope
import ru.undframe.notes.services.AuthService


@Module
class RetrofitModule {
    @BaseSingletonScope
    @Provides
    fun authInstanceRetrofit(): AuthService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("http://undframe.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build().create(AuthService::class.java)
    }
}