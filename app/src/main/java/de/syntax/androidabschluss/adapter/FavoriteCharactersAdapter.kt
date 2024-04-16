package de.syntax.androidabschluss.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.data.local.EntityCharacters
import de.syntax.androidabschluss.databinding.DesignHomeAnimeCharactersBinding
import de.syntax.androidabschluss.ui.FavoriteFragmentDirections

// Adapterklasse für einen RecyclerView, der eine Liste von Lieblingscharakteren anzeigt.
class FavoriteCharactersAdapter(private val dataset: List<EntityCharacters>):
    RecyclerView.Adapter<FavoriteCharactersAdapter.FavoriteCharactersViewHolder>() {

    // ViewHolder-Klasse, die die Bindungen für das Layout der Charaktere enthält.
    inner class FavoriteCharactersViewHolder(val binding: DesignHomeAnimeCharactersBinding): RecyclerView.ViewHolder(binding.root)

    // Erstellt neue Ansichten (aufgerufen vom Layout-Manager).
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCharactersViewHolder {
        val binding = DesignHomeAnimeCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteCharactersViewHolder(binding)
    }

    // Gibt die Größe des Datensatzes zurück, die Anzahl der Elemente, die im RecyclerView angezeigt werden.
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Verknüpft die Daten mit einer Ansicht (aufgerufen vom Layout-Manager).
    override fun onBindViewHolder(holder: FavoriteCharactersViewHolder, position: Int) {
        val item = dataset[position]
        holder.apply {
            binding.imageView.glideImageSet(item.image_url) // Setzt das Bild des Charakters.
            binding.textView.text = item.name // Setzt den Namen des Charakters.

            // Klick-Listener, der den Benutzer zur Detailansicht des ausgewählten Charakters führt.
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
