package de.syntax.androidabschluss.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.data.local.EntityAnime
import de.syntax.androidabschluss.databinding.DesignHomeAnimeCharactersBinding
import de.syntax.androidabschluss.databinding.FragmentFavoriteBinding
import de.syntax.androidabschluss.ui.FavoriteFragmentDirections
import de.syntax.androidabschluss.viewmodel.MainViewModel

class FavoriteAnimeAdapter(private val dataset: List<EntityAnime>) :
    RecyclerView.Adapter<FavoriteAnimeAdapter.FavoriteAnimeViewHolder>() {
    inner class FavoriteAnimeViewHolder(val binding: DesignHomeAnimeCharactersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAnimeViewHolder {
        val binding = DesignHomeAnimeCharactersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteAnimeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: FavoriteAnimeViewHolder, position: Int) {
        val item = dataset[position]
        holder.apply {
            binding.imageView.glideImageSet(item.image_url)
            binding.textView.text = item.title

            itemView.setOnClickListener { view ->
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToAnimeDetailFragment(item.mal_id)
                view.findNavController().navigate(action)
            }
        }
    }
}