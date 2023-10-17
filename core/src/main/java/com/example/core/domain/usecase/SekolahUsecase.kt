package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Sekolah
import kotlinx.coroutines.flow.Flow

interface SekolahUsecase {
    fun getAllSekolah(): Flow<Resource<List<Sekolah>>>
    fun getFavoriteSekolah(): Flow<List<Sekolah>>
    fun setFavoriteSekolah(
        sekolah: Sekolah, state:Boolean
    )
}