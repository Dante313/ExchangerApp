package com.example.walletexchangerapp.ui.presenter.screens.sort

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walletexchangerapp.domain.model.Sort
import com.example.walletexchangerapp.domain.repository.SortRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SortViewModel @Inject constructor(
    private val sortRepository: SortRepository
) : ViewModel() {

    val sortState = sortRepository.sortFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Sort.AMOUNT_ASC
    )

    fun setSort(sort: Sort) {
        viewModelScope.launch { sortRepository.setSort(sort) }
    }
}