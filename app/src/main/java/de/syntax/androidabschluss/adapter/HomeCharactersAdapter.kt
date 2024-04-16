package de.syntax.androidabschluss.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.data.models.CharactersData
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.databinding.DesignHomeAnimeCharactersBinding
import de.syntax.androidabschluss.ui.HomeFragmentDirections

class HomeCharactersAdapter(private val dataset: List<CharactersData>):RecyclerView.Adapter<HomeCharactersAdapter.HomeCharacterViewHolder>() {
    inner class HomeCharacterViewHolder(val binding: DesignHomeAnimeCharactersBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCharacterViewHolder {
        val binding = DesignHomeAnimeCharactersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeCharacterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: HomeCharacterViewHolder, position: Int) {
        val item = dataset[position]
        holder.apply {
            item.images?.jpg?.let { binding.imageView.glideImageSet(it.image_url) }
            binding.textView.text = item.name

            itemView.setOnClickListener {view->
                val action = item.mal_id?.let {
                    HomeFragmentDirections.actionHomeFragmentToCharacterDetailFragment(
                        it
                    )
                }
                if (action != null) {
                    view.findNavController().navigate(action)
                }
            }
        }
    }
}