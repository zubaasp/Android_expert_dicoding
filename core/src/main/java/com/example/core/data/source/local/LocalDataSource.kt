package com.example.core.data.source.local

import com.example.core.data.source.local.entity.SekolahEntity
import com.example.core.data.source.local.room.SekolahDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val sekolahDao: SekolahDao) {
    fun getAllSekolah(): Flow<List<SekolahEntity>> = sekolahDao.getAllSekolah()

    fun getFavoriteSekolah(): Flow<List<SekolahEntity>> = sekolahDao.getFavoriteSekolah()

    suspend fun insertSekolah(sekolahList: List<SekolahEntity>) = sekolahDao.insertSekolah(sekolahList)

    fun setFavoriteTourism(sekolah: SekolahEntity, newState: Boolean) {
        sekolah.isFavorite = newState
        sekolahDao.updateFavoriteSekolah(sekolah)
    }
}