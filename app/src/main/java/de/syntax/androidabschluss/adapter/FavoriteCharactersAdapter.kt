package de.syntax.androidabschluss.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.data.local.EntityCharacters
import de.syntax.androidabschluss.ui.FavoriteFragmentDirections

class FavoriteCharactersAdapter(private val list: List<EntityCharacters>): RecyclerView.Adapter<FavoriteCharactersAdapter.d>() {
    inner class d(v: View): RecyclerView.ViewHolder(v) {
        val image : ImageView = v.findViewById(R.id.imageView)
        val text : TextView = v.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): d {
        val l = LayoutInflater.from(parent.context).inflate(R.layout.design_home_anime_characters,parent,false)
        return d(l)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: d, position: Int) {
        val item = list[position]
        holder.apply {
            image.glideImageSet(item.image_url)
            text.text = item.name

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