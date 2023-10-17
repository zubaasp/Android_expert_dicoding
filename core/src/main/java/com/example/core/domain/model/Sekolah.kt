package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sekolah(
    val id:String,
    val npsn: String,
    val sekolah: String,
    val alamatJalan: String,
    val propinsi: String,
    val kecamatan: String,
    val isFavorite: Boolean
): Parcelable