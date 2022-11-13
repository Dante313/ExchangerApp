package com.example.walletexchangerapp.data.repository

import com.example.walletexchangerapp.data.database.dao.PopularDao
import com.example.walletexchangerapp.data.database.entity.RatesEntity
import com.example.walletexchangerapp.data.database.entity.RatesEntity.Companion.toRates
import com.example.walletexchangerapp.data.database.entity.WalletEntity
import com.example.walletexchangerapp.data.database.entity.WalletEntity.Companion.toWallet
import com.example.walletexchangerapp.data.network.api.ExchangerApi
import com.example.walletexchangerapp.data.network.api.safeApiCall
import com.example.walletexchangerapp.data.network.model.RemoteWallet
import com.example.walletexchangerapp.data.network.model.RemoteWallet.Companion.toWallet
import com.example.walletexchangerapp.domain.model.Rates
import com.example.walletexchangerapp.domain.model.Rates.Companion.toRatesEntity
import com.example.walletexchangerapp.domain.model.ResponseResult
import com.example.walletexchangerapp.domain.model.Wallet
import com.example.walletexchangerapp.domain.model.Wallet.Companion.toWalletEntity
import com.example.walletexchangerapp.domain.repository.PopularRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val exchangerApi: ExchangerApi,
    private val popularDao: PopularDao
) : PopularRepository {

    override suspend fun getPopularWalletList(wallet: String) {
        withContext(Dispatchers.IO) {
            try {
                val remotePopularResponse = exchangerApi.getPopularWalletList(wallet = wallet)
                if (remotePopularResponse.isSuccessful) {
                    val remotePopular = remotePopularResponse.body()?.toWallet()
                    val localWallet = popularDao.getPopularWalletEntityFlow().last()
                    val localRates = popularDao.getRatesByWalletEntity(localWallet.id)

                    localRates?.let {
                        val localPopular = createLocalPopular(localWallet, localRates)

                        if (remotePopular != null && remotePopular != localPopular) {
                            popularDao.deleteWalletEntity(localWallet)
                            popularDao.deleteRatesEntity(localRates)

                            val walletId = popularDao.insertWalletEntity(remotePopular.toWalletEntity())
                            popularDao.insertRatesEntity(remotePopular.rates.toRatesEntity(walletId))
                        }
                    }
                }
            } catch (_: IOException) {}
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val popularWalletFlow: Flow<Wallet?> =
        popularDao.getPopularWalletEntityFlow().flatMapLatest { walletEntity ->
            ratesByWalletFlow.map { rates ->
                rates?.let { walletEntity.toWallet(rates) }
            }
        }

    override val ratesByWalletFlow: Flow<Rates?> = popularWalletFlow.map { walletEntity ->
        walletEntity?.let { popularDao.getRatesByWalletEntity(walletEntity.id)?.toRates() }
    }

    override suspend fun insertWallet(wallet: Wallet) {
        withContext(Dispatchers.IO) {
            popularDao.insertWalletEntity(wallet.toWalletEntity())
        }
    }

    override suspend fun insertRates(rates: Rates, walletId: Long) {
        withContext(Dispatchers.IO) {
            popularDao.insertRatesEntity(rates.toRatesEntity(walletId))
        }
    }

    override suspend fun updateWallet(wallet: Wallet) {
        withContext(Dispatchers.IO) {
            popularDao.updateWalletEntity(wallet.toWalletEntity())
        }
    }

    override suspend fun updateRates(rates: Rates, walletId: Long) {
        withContext(Dispatchers.IO) {
            popularDao.updateRatesEntity(rates.toRatesEntity(walletId))
        }
    }

    override suspend fun deleteWallet(wallet: Wallet) {
        withContext(Dispatchers.IO) {
            popularDao.deleteWalletEntity(wallet.toWalletEntity())
        }
    }

    override suspend fun deleteRates(rates: Rates, walletId: Long) {
        withContext(Dispatchers.IO) {
            popularDao.deleteRatesEntity(rates.toRatesEntity(walletId))
        }
    }

    private fun createLocalPopular(localWallet: WalletEntity, localRates: RatesEntity): Wallet {
        return Wallet(
            base = localWallet.base,
            rates = localRates.toRates(),
            isFavourite = false
        )
    }
}