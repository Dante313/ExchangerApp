package com.example.walletexchangerapp.data.network.di

import com.example.walletexchangerapp.data.network.api.ExchangerApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providerClientBuilder(): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
    }.build()

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