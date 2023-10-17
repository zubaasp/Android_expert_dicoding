package com.example.core.data.source.remote

import com.example.core.data.source.remote.network.APIResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.ResponSekolah
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllSekolah(): Flow<APIResponse<ResponSekolah>> = flow {
        try {
            val respons = apiService.getSekolah()
            if (respons.dataSekolah.isNotEmpty()){
                emit(APIResponse.Success(respons))
            }
            else emit(APIResponse.Empty)
        }catch(error:Exception) {
            emit(APIResponse.Error(error.toString()))
        }
    }.flowOn(Dispatchers.IO)
}