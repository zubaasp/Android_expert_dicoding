package com.example.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.SekolahUsecase

class FavoriteViewModel(sekolahUsecase: SekolahUsecase) : ViewModel() {
    val favorite = sekolahUsecase.getFavoriteSekolah().asLiveData()
}