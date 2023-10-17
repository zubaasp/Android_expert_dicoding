package com.example.expert

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expert.databinding.ActivityMainBinding
import com.example.expert.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFavorite.setOnClickListener {
            val uri = Uri.parse("expert://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.HomeFragment, HomeFragment())
            .commit()
    }
}