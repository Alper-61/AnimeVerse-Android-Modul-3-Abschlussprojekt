package de.syntax.androidabschluss.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.data.models.TopReviewsData
import de.syntax.androidabschluss.databinding.DesignHomeAnimeCharactersBinding
import de.syntax.androidabschluss.databinding.DesignHomeTopreviewsBinding
import de.syntax.androidabschluss.ui.HomeFragmentDirections

class HomeTopReviewsAdapter(private val dataset: List<TopReviewsData>) :
    RecyclerView.Adapter<HomeTopReviewsAdapter.HomeTopReviewsViewHolder>() {
    inner class HomeTopReviewsViewHolder(val binding: DesignHomeTopreviewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTopReviewsViewHolder {
        val binding = DesignHomeTopreviewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeTopReviewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HomeTopReviewsViewHolder, position: Int) {
        val item = dataset[position]
        holder.apply {
            item.entry?.images?.jpg?.let { binding.imageView2.glideImageSet(it.image_url) }
            binding.textView2.text =
                itemView.context.getString(R.string.type) + item.type + "\n" + itemView.context.getString(
                    R.string.title
                ) + (item.entry?.title ?: itemView.context.getString(R.string.unknown))
            binding.textView3.text = item.review

            itemView.setOnClickListener {view->
                val action = HomeFragmentDirections.actionHomeFragmentToTopReviewsDetailFragment(item)
                view.findNavController().navigate(action)
            }
        }
    }
}