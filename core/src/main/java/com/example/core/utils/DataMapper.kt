package com.example.core.utils

import com.example.core.data.source.local.entity.SekolahEntity
import com.example.core.data.source.remote.response.ResponSekolah
import com.example.core.domain.model.Sekolah

object DataMapper {
    fun mapResponsesToEntities(input: ResponSekolah): List<SekolahEntity> {
        val sekolahList = ArrayList<SekolahEntity>()
        input.dataSekolah.map {
            val sekolah = SekolahEntity(
                id = it.id,
                sekolah = it.sekolah,
                alamatJalan = it.alamatJalan,
                npsn = it.npsn,
                kecamatan = it.kecamatan,
                propinsi = it.propinsi
            )
            sekolahList.add(sekolah)
        }

        return sekolahList
    }

    fun mapEntitiesToDomain(input: List<SekolahEntity>): List<Sekolah> =
        input.map {
            Sekolah(
                id = it.id,
                sekolah = it.sekolah,
                alamatJalan = it.alamatJalan,
                npsn = it.npsn,
                kecamatan = it.kecamatan,
                propinsi = it.propinsi,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Sekolah) = SekolahEntity(
        id = input.id,
        sekolah = input.sekolah,
        alamatJalan = input.alamatJalan,
        npsn = input.npsn,
        kecamatan = input.kecamatan,
        propinsi = input.propinsi,
        isFavorite = input.isFavorite
    )
}