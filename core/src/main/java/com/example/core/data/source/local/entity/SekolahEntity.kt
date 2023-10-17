package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sekolah")
data class SekolahEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id:String,
    @ColumnInfo("npsn")
    val npsn: String,
    @ColumnInfo("sekolah")
    val sekolah: String,
    @ColumnInfo("alamatJalan")
    val alamatJalan: String,
    @ColumnInfo("propinsi")
    val propinsi: String,
    @ColumnInfo("kecamatan")
    val kecamatan: String,
    @ColumnInfo("isFavorite")
    var isFavorite: Boolean = false
)