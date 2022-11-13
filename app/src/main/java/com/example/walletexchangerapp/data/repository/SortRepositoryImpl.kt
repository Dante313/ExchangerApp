package com.example.walletexchangerapp.data.repository

import com.example.walletexchangerapp.data.store.SortStore
import com.example.walletexchangerapp.domain.model.Sort
import com.example.walletexchangerapp.domain.repository.SortRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SortRepositoryImpl @Inject constructor(private val sortStore: SortStore) : SortRepository {

    override val sortFlow: Flow<Sort> = sortStore.sortFlow

    override suspend fun setSort(sort: Sort) = withContext(Dispatchers.IO) {
        sortStore.setSort(sort)
    }
}