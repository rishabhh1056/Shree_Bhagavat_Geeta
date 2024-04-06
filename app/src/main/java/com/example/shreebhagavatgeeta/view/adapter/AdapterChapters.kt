package com.example.shreebhagavatgeeta.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shreebhagavatgeeta.databinding.ItemViewChaptersBinding
import com.example.shreebhagavatgeeta.models.chaptersItem

class AdapterChapters(val onChapterIVClicked: (chaptersItem) -> Unit) : RecyclerView.Adapter<AdapterChapters.ChaptersViewHolder>(){

    val diffUtil = object : DiffUtil.ItemCallback<chaptersItem>(){
        override fun areItemsTheSame(oldItem: chaptersItem, newItem: chaptersItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: chaptersItem, newItem: chaptersItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this , diffUtil)

    class ChaptersViewHolder(val binding: ItemViewChaptersBinding):ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChaptersViewHolder {
        return ChaptersViewHolder(ItemViewChaptersBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ChaptersViewHolder, position: Int) {
        val chapters :chaptersItem = differ.currentList[position]

        holder.binding.apply {
           tvChapterNumber.text = "Chapter ${chapters.chapter_number}"
            tvChapterTitle.text = chapters.name_translated
            tvChapterDescripation.text = chapters.chapter_summary
            tvVerseCount.text = chapters.verses_count.toString()
        }

        holder.binding.linearLayout2.setOnClickListener {
            onChapterIVClicked(chapters)
        }
    }
}