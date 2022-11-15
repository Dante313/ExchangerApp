package com.example.walletexchangerapp.data.repository

import com.example.walletexchangerapp.data.network.api.ExchangerApi
import com.example.walletexchangerapp.data.network.model.RemoteWallet.Companion.toWallet
import com.example.walletexchangerapp.domain.model.ResponseResult
import com.example.walletexchangerapp.domain.model.Wallet
import com.example.walletexchangerapp.domain.repository.PopularRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val exchangerApi: ExchangerApi
) : PopularRepository {

    override suspend fun getPopularWalletList(wallet: String): ResponseResult<Wallet> {
        return withContext(Dispatchers.IO) {
            try {
                val remotePopularResponse = exchangerApi.getPopularWalletList(wallet = wallet)
                if (remotePopularResponse.isSuccessful) {
                    val popularWallet = remotePopularResponse.body()?.toWallet()
                    ResponseResult.Success(popularWallet)
                } else {
                    ResponseResult.Failure(remotePopularResponse.message(), remotePopularResponse.code())
                }
            } catch (exception: IOException) {
                ResponseResult.Error(exception)
            }
        }
    }
}