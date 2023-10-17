package com.example.expert.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Sekolah
import com.example.core.domain.usecase.SekolahUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val sekolahUsecase: SekolahUsecase) : ViewModel() {
    private val _favorite = MutableLiveData(false)
    val favorite: LiveData<Boolean> get() = _favorite

    fun setFavoriteSekolah(sekolah: Sekolah, status: Boolean) {
        sekolahUsecase.setFavoriteSekolah(sekolah, status)
        _favorite.value = status
    }

    fun setFavorite(isFavorite: Boolean) {
        _favorite.value = isFavorite
    }
}