package com.example.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core.data.source.local.entity.SekolahEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SekolahDao {
    @Query("SELECT * FROM Sekolah")
    fun getAllSekolah(): Flow<List<SekolahEntity>>

    @Query("SELECT * FROM Sekolah WHERE isFavorite = 1")
    fun getFavoriteSekolah(): Flow<List<SekolahEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSekolah(sekolah: List<SekolahEntity>)

    @Update
    fun updateFavoriteSekolah(sekolah: SekolahEntity)
}