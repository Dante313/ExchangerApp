package com.example.walletexchangerapp.domain.usecase

import com.example.walletexchangerapp.domain.model.Favourite
import com.example.walletexchangerapp.domain.model.Rate
import com.example.walletexchangerapp.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.last

class AddOrDeleteFavouriteUseCase(
    private val favouriteRepository: FavouriteRepository
) {
    suspend operator fun invoke(rate: Rate) {
        favouriteRepository.getFavourites().let { favourites ->
            if (favourites.contains(Favourite(rate.wallet))) {
                favouriteRepository.deleteFavourite(rate.wallet)
            } else {
                favouriteRepository.insertFavourite(Favourite(rate.wallet))
            }
        }
    }
}