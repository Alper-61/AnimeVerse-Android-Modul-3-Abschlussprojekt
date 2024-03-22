package de.syntax.androidabschluss.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.data.models.AnimeData
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.databinding.DesignHomeAnimeCharactersBinding
import de.syntax.androidabschluss.ui.AnimeDetailFragment
import de.syntax.androidabschluss.ui.HomeFragmentDirections
import de.syntax.androidabschluss.viewmodel.MainViewModel

class HomeAnimeAdapter(private val dataset: List<AnimeData>,
    private val viewModel : MainViewModel
    ) :
    RecyclerView.Adapter<HomeAnimeAdapter.HomeAnimeViewHolder>() {
    inner class HomeAnimeViewHolder(val binding: DesignHomeAnimeCharactersBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAnimeViewHolder {
        val binding = DesignHomeAnimeCharactersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeAnimeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: HomeAnimeViewHolder, position: Int) {
        val item = dataset[position]
        holder.binding.imageView.load(item.images)
        holder.binding.textView.text = item.title

        holder.itemView.setOnClickListener {
            viewModel.getAnimeList(it.id)
            holder.itemView.findNavController().navigate(R.id.action_homeFragment_to_animeDetailFragment)
        }

    }


}