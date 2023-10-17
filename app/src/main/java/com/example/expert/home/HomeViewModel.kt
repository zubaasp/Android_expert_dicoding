package com.example.expert.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.SekolahUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(sekolahUsecase: SekolahUsecase) : ViewModel() {
    val data = sekolahUsecase.getAllSekolah().asLiveData()
}