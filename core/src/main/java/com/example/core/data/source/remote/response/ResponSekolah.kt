package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponSekolah(
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("dataSekolah")
    val dataSekolah: List<ResponItem>
)

data class ResponItem(
    @field:SerializedName("id")
    val id:String,
    @field:SerializedName("npsn")
    val npsn: String,
    @field:SerializedName("sekolah")
    val sekolah: String,
    @field:SerializedName("alamat_jalan")
    val alamatJalan: String,
    @field:SerializedName("propinsi")
    val propinsi: String,
    @field:SerializedName("kecamatan")
    val kecamatan: String
)