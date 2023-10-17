package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Sekolah
import com.example.core.domain.repository.SekolahRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SekolahInteractor @Inject constructor(
    private val sekolahRepositoryImpl: SekolahRepositoryImpl
): SekolahUsecase {
    override fun getAllSekolah(): Flow<Resource<List<Sekolah>>> {
        return sekolahRepositoryImpl.getAllSekolah()
    }

    override fun getFavoriteSekolah(): Flow<List<Sekolah>> {
        return sekolahRepositoryImpl.getFavoriteSekolah()
    }

    override fun setFavoriteSekolah(sekolah: Sekolah, state: Boolean) {
        sekolahRepositoryImpl.setFavoriteSekolah(sekolah,state)
    }
}