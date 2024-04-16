package de.syntax.androidabschluss.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.data.local.EntityCharacters
import de.syntax.androidabschluss.databinding.DesignHomeAnimeCharactersBinding
import de.syntax.androidabschluss.databinding.FragmentFavoriteBinding
import de.syntax.androidabschluss.ui.FavoriteFragmentDirections
import de.syntax.androidabschluss.viewmodel.MainViewModel

class FavoriteCharactersAdapter(private val dataset: List<EntityCharacters>):
    RecyclerView.Adapter<FavoriteCharactersAdapter.FavoriteCharactersViewHolder>() {
    inner class FavoriteCharactersViewHolder(val binding: DesignHomeAnimeCharactersBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCharactersViewHolder {
        val binding = DesignHomeAnimeCharactersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteCharactersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: FavoriteCharactersViewHolder, position: Int) {
        val item = dataset[position]
        holder.apply {
            binding.imageView.glideImageSet(item.image_url)
            binding.textView.text = item.name
            itemView.setOnClickListener { view ->
                val action =
                    FavoriteFragmentDirections.actionFavoriteFragmentToCharacterDetailFragment(
                        item.mal_id
                    )
                view.findNavController().navigate(action)
            }
        }
    }
}