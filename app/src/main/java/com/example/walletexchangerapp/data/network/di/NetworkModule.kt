package com.example.walletexchangerapp.data.network.di

import com.example.walletexchangerapp.BuildConfig
import com.example.walletexchangerapp.data.network.api.ExchangerApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providerClientBuilder(): OkHttpClient {
        val HEADER_AUTHORIZATION = "apikey"
        val API_KEY = "y0IcT03LruY9vdPpvSA7d7v9WFchM0DS"

        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            addInterceptor { chain ->
                val originalRequest = chain.request()
                val interceptedRequest = originalRequest.newBuilder()
                    .header(HEADER_AUTHORIZATION, API_KEY).build()

                chain.proceed(interceptedRequest)
            }
        }.build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val BASE_URL = "https://api.apilayer.com/exchangerates_data/"
        val CONTENT_TYPE = "application/json"
        val json = Json { ignoreUnknownKeys = true }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory(CONTENT_TYPE.toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun providerExchangerApi(retrofit: Retrofit): ExchangerApi {
        return retrofit.create(ExchangerApi::class.java)
    }
}