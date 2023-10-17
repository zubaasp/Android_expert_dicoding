package com.example.favorite.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.di.FavoriteModule
import com.example.core.domain.model.Sekolah
import com.example.core.ui.SekolahAdapter
import com.example.expert.detail.DetailActivity
import com.example.favorite.databinding.ActivityFavoriteBinding
import com.example.favorite.di.DaggerFavoriteComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val sekolahAdapter by lazy { SekolahAdapter() }

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModule::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.rvFavorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = sekolahAdapter
        }

        viewModel.favorite.observe(this) { sekolah ->
            binding.rvFavorite.visibility = if (sekolah.isNotEmpty()) View.VISIBLE else View.INVISIBLE
            binding.viewEmpty.root.visibility = if (sekolah.isNotEmpty()) View.GONE else View.VISIBLE
            sekolahAdapter.differ.submitList(sekolah)
        }

        sekolahAdapter.setOnItemClick(object : SekolahAdapter.OnItemClick {
            override fun onItemClick(data: Sekolah) {
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(intent)
            }
        })
    }
}