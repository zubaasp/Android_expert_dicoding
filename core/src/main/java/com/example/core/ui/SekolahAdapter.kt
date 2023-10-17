package com.example.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.databinding.ItemRowSekolahBinding
import com.example.core.domain.model.Sekolah

class SekolahAdapter : RecyclerView.Adapter<SekolahAdapter.ListViewHolder>() {
    private lateinit var binding: ItemRowSekolahBinding
    private lateinit var onItemClickCallback: OnItemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SekolahAdapter.ListViewHolder {
        binding = ItemRowSekolahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder()
    }

    override fun onBindViewHolder(holder: SekolahAdapter.ListViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ListViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Sekolah) {
            with(binding) {
                NamaSekolah.text = data.sekolah
                AlamatSekolah.text = data.alamatJalan
                cvSekolah.setOnClickListener {
                    onItemClickCallback.onItemClick(data)
                }
            }
        }
    }

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClickCallback = onItemClick
    }

    interface OnItemClick {
        fun onItemClick(data: Sekolah)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Sekolah>() {
        override fun areItemsTheSame(oldItem: Sekolah, newItem: Sekolah): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Sekolah, newItem: Sekolah): Boolean = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallback)
}