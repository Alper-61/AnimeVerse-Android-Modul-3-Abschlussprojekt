package de.syntax.androidabschluss.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.data.local.EntityAnime
import de.syntax.androidabschluss.databinding.DesignHomeAnimeCharactersBinding
import de.syntax.androidabschluss.ui.FavoriteFragmentDirections

// Adapterklasse für einen RecyclerView, der eine Liste von Lieblings-Anime-Charakteren anzeigt.
class FavoriteAnimeAdapter(private val dataset: List<EntityAnime>) :
    RecyclerView.Adapter<FavoriteAnimeAdapter.FavoriteAnimeViewHolder>() {

    // ViewHolder-Klasse, die die Bindungen für das Layout der Anime-Charaktere enthält.
    inner class FavoriteAnimeViewHolder(val binding: DesignHomeAnimeCharactersBinding) : RecyclerView.ViewHolder(binding.root)

    // Erstellt neue Ansichten (aufgerufen vom Layout-Manager).
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAnimeViewHolder {
        val binding = DesignHomeAnimeCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteAnimeViewHolder(binding)
    }

    // Gibt die Größe des Datensatzes zurück, die Anzahl der Elemente, die im RecyclerView angezeigt werden.
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Verknüpft die Daten mit einer Ansicht (aufgerufen vom Layout-Manager).
    override fun onBindViewHolder(holder: FavoriteAnimeViewHolder, position: Int) {
        val item = dataset[position]
        holder.apply {
            binding.imageView.glideImageSet(item.image_url) // Setzt das Bild der Anime-Figur.
            binding.textView.text = item.title // Setzt den Titel der Anime-Figur.

            // Klick-Listener, der den Benutzer zur Detailansicht des ausgewählten Anime führt.
            itemView.setOnClickListener { view ->
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToAnimeDetailFragment(item.mal_id)
                view.findNavController().navigate(action)
            }
        }
    }
}
