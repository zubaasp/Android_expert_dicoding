package com.example.expert.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.core.domain.model.Sekolah
import com.example.expert.R
import com.example.expert.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModels<DetailViewModel>()
    private var isFavoriteClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sekolah = intent.getParcelableExtra<Sekolah>(EXTRA_DATA)

        showData(sekolah)

        viewModel.favorite.observe(this) { isFavorite ->
            binding.fabFavorite.setImageResource(setFavorite(isFavorite))
            if (isFavoriteClicked) showMessage(isFavorite)
        }
    }

    private fun showData(data: Sekolah?) {
        data?.let { sekolahData ->
            viewModel.setFavorite(sekolahData.isFavorite)

            binding.tvSekolah.text = sekolahData.sekolah
            binding.tvNpsn.text = sekolahData.npsn
            binding.tvAlamat.text = sekolahData.alamatJalan
            binding.tvKecamatan.text = sekolahData.kecamatan
            binding.tvPropinsi.text= sekolahData.propinsi

            var isFavorited = sekolahData.isFavorite
            binding.fabFavorite.setOnClickListener {
                isFavoriteClicked = true
                isFavorited = !isFavorited
                viewModel.setFavoriteSekolah(data, isFavorited)
            }
        }
    }

    private fun showMessage(isFavorite: Boolean) {
        Toast.makeText(
            this@DetailActivity,
            getString(if (isFavorite) R.string.tambah_favorite else R.string.hapus_favorite),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setFavorite(isFavorite: Boolean): Int {
        return if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}