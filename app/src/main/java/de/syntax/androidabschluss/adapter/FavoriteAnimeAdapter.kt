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
import de.syntax.androidabschluss.databinding.FragmentFavoriteBinding
import de.syntax.androidabschluss.ui.FavoriteFragmentDirections
import de.syntax.androidabschluss.viewmodel.MainViewModel

class FavoriteAnimeAdapter(private val dataset: List<EntityAnime>) :
    RecyclerView.Adapter<FavoriteAnimeAdapter.FavoriteAnimeViewHolder>() {
    inner class FavoriteAnimeViewHolder(binding: FragmentFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.rvAnime.findViewById(R.id.imageView)
        val text: TextView = binding.animeTv.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAnimeViewHolder {
        val binding = FragmentFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteAnimeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: FavoriteAnimeViewHolder, position: Int) {
        val item = dataset[position]
        holder.apply {
            image.glideImageSet(item.image_url)
            text.text = item.title

            itemView.setOnClickListener { view ->
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToAnimeDetailFragment(item.mal_id)
                view.findNavController().navigate(action)
            }
        }
    }
}