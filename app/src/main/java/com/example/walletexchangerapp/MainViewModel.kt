package com.example.walletexchangerapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walletexchangerapp.domain.repository.PopularRepository
import com.example.walletexchangerapp.domain.repository.SortRepository
import com.example.walletexchangerapp.domain.usecase.GetFilteredWalletUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFilteredWalletUseCase: GetFilteredWalletUseCase
) : ViewModel() {

    fun getPopularWallet(base: String) {
        viewModelScope.launch {
            getFilteredWalletUseCase.getWalletList(base)
        }
    }
}