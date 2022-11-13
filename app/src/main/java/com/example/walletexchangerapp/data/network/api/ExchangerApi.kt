package com.example.walletexchangerapp.data.network.api

import com.example.walletexchangerapp.data.network.model.RemoteWallet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ExchangerApi {

    // убрать апи кей
    @GET("/latest")
    suspend fun getPopularWalletList(
        @Header(HEADER_AUTHORIZATION) apikey: String = API_KEY,
        @Query("wallet") wallet: String
    ) : Response<RemoteWallet>

    companion object {
        private const val HEADER_AUTHORIZATION = "apikey"
        private const val API_KEY = "y0IcT03LruY9vdPpvSA7d7v9WFchM0DS"
    }
}