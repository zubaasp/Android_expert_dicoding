package com.example.expert.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.core.domain.model.Sekolah
import com.example.core.ui.SekolahAdapter
import com.example.expert.databinding.FragmentHomeBinding
import com.example.expert.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val sekolahAdapter by lazy { SekolahAdapter() }
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null) {
            sekolahAdapter.setOnItemClick(object:SekolahAdapter.OnItemClick{
                override fun onItemClick(data: Sekolah) {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA, data)
                    startActivity(intent)
                }
            })

            binding?.rvSekolah?.layoutManager = LinearLayoutManager(context)
            binding?.rvSekolah?.adapter = sekolahAdapter

            viewModel.data.observe(viewLifecycleOwner){sekolah ->
                if(sekolah!=null){
                    when(sekolah){
                        is Resource.Loading -> {
                            binding?.ProgressBar?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding?.ProgressBar?.visibility = View.GONE
                            sekolahAdapter.differ.submitList(sekolah.data)
                        }
                        is Resource.Error -> {
                            binding?.ProgressBar?.visibility = View.GONE
                            binding?.viewError?.root?.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}