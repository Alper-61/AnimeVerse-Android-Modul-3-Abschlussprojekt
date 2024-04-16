package de.syntax.androidabschluss.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.data.models.CharactersData
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.data.models.AnimeData
import de.syntax.androidabschluss.databinding.DesignHomeTopAnimeCharactersBinding
import de.syntax.androidabschluss.databinding.FragmentTopCharactersBinding
import de.syntax.androidabschluss.ui.Utils.RankingFragmentDirections
import de.syntax.androidabschluss.viewmodel.MainViewModel

class RankingTopCharactersAdapter(private val list: List<CharactersData>) :
    RecyclerView.Adapter<RankingTopCharactersAdapter.RankingTopViewHolder>() {
    inner class RankingTopViewHolder(val binding: DesignHomeTopAnimeCharactersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingTopViewHolder {
        val binding = DesignHomeTopAnimeCharactersBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return RankingTopViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RankingTopViewHolder, position: Int) {
        val item = list[position]
        holder.apply {
            item.images?.jpg?.let { it.image_url.let { it1 -> binding.imageView2.glideImageSet(it1) } }
            binding.textView2.text =
                itemView.context.getString(R.string.name) + item.name
            binding.textView3.text = item.about

            itemView.setOnClickListener {view->
                val action = item.mal_id?.let {
                    RankingFragmentDirections.actionRankingFragmentToCharacterDetailFragment(
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
