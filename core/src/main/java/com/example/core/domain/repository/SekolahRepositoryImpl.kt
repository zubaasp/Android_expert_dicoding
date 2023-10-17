package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Sekolah
import kotlinx.coroutines.flow.Flow

interface SekolahRepositoryImpl {
    fun getAllSekolah(): Flow<Resource<List<Sekolah>>>
    fun getFavoriteSekolah(): Flow<List<Sekolah>>
    fun setFavoriteSekolah(sekolah: Sekolah, state: Boolean)
}