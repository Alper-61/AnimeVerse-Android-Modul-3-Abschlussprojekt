package de.syntax.androidabschluss.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.data.models.AnimeData
import de.syntax.androidabschluss.databinding.DesignHomeAnimeCharactersBinding
import de.syntax.androidabschluss.ui.HomeFragmentDirections

// Adapterklasse für einen RecyclerView, der eine Liste von Animes anzeigt.
class HomeAnimeAdapter(private val dataset: List<AnimeData>) :
    RecyclerView.Adapter<HomeAnimeAdapter.HomeAnimeViewHolder>() {

    // ViewHolder-Klasse, die die Bindungen für das Layout der Anime-Daten enthält.
    inner class HomeAnimeViewHolder(val binding: DesignHomeAnimeCharactersBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Erstellt neue Ansichten (aufgerufen vom Layout-Manager).
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAnimeViewHolder {
        val binding = DesignHomeAnimeCharactersBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return HomeAnimeViewHolder(binding)
    }

    // Gibt die Anzahl der Elemente im Datensatz zurück, also wie viele Elemente im RecyclerView angezeigt werden.
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Verknüpft die Daten mit einer Ansicht (aufgerufen vom Layout-Manager).
    override fun onBindViewHolder(holder: HomeAnimeViewHolder, position: Int) {
        val item = dataset[position]

        // Setzt das Bild des Anime mithilfe von Glide, wenn eine URL verfügbar ist.
        item.images?.jpg?.image_url?.let { holder.binding.imageView.glideImageSet(it) }

        // Setzt den Titel des Anime.
        holder.binding.textView.text = item.title

        // Klick-Listener, der den Benutzer zur Detailansicht des ausgewählten Anime führt.
        holder.itemView.setOnClickListener { view ->
            val action = item.mal_id?.let {
                HomeFragmentDirections.actionHomeFragmentToAnimeDetailFragment(it)
            }
            if (action != null) {
                view.findNavController().navigate(action)
            }
        }
    }
}
