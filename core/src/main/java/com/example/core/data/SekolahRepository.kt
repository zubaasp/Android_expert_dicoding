package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.APIResponse
import com.example.core.data.source.remote.response.ResponSekolah
import com.example.core.domain.model.Sekolah
import com.example.core.domain.repository.SekolahRepositoryImpl
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SekolahRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): SekolahRepositoryImpl {
    override fun getAllSekolah(): Flow<Resource<List<Sekolah>>> =
        object : NetworkBoundResource<List<Sekolah>, ResponSekolah>() {
            override fun loadFromDB(): Flow<List<Sekolah>> {
                return localDataSource.getAllSekolah().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Sekolah>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<APIResponse<ResponSekolah>> =
                remoteDataSource.getAllSekolah()

            override suspend fun saveCallResult(data: ResponSekolah) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertSekolah(tourismList)
            }
        }.asFlow()


    override fun getFavoriteSekolah(): Flow<List<Sekolah>> {
        return localDataSource.getFavoriteSekolah().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteSekolah(sekolah: Sekolah, state: Boolean) {
        val prayEntity = DataMapper.mapDomainToEntity(sekolah)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTourism(prayEntity, state)
        }
    }
}