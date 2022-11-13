package com.example.walletexchangerapp.domain.repository

import com.example.walletexchangerapp.domain.model.Sort
import kotlinx.coroutines.flow.Flow

interface SortRepository {

    val sortFlow: Flow<Sort>

    suspend fun setSort(sort: Sort)
}