package com.example.walletexchangerapp

import androidx.lifecycle.ViewModel
import com.example.walletexchangerapp.domain.repository.PopularRepository
import com.example.walletexchangerapp.domain.repository.SortRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val popularRepository: PopularRepository,
    private val sortRepository: SortRepository
) : ViewModel() {

}