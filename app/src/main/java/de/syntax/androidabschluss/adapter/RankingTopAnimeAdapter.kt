package de.syntax.androidabschluss.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.data.models.AnimeData
import de.syntax.androidabschluss.databinding.DesignHomeTopAnimeCharactersBinding
import de.syntax.androidabschluss.ui.Utils.RankingFragmentDirections

// Adapterklasse für einen RecyclerView, der eine Liste der top-bewerteten Anime anzeigt.
class RankingTopAnimeAdapter(private val dataset: List<AnimeData>) :
    RecyclerView.Adapter<RankingTopAnimeAdapter.RankingViewHolder>() {

    // ViewHolder-Klasse, die die Bindungen für das spezifische Layout der top-bewerteten Anime enthält.
    inner class RankingViewHolder(val binding: DesignHomeTopAnimeCharactersBinding) : RecyclerView.ViewHolder(binding.root)

    // Erstellt neue Ansichten (aufgerufen vom Layout-Manager).
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val binding = DesignHomeTopAnimeCharactersBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return RankingViewHolder(binding)
    }

    // Gibt die Anzahl der Elemente in der Liste zurück, also wie viele Elemente im RecyclerView angezeigt werden.
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Verknüpft die Daten mit einer Ansicht (aufgerufen vom Layout-Manager).
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        val item = dataset[position]
        holder.apply {
            // Setzt das Bild des Anime mithilfe von Glide, wenn eine Bild-URL vorhanden ist.
            item.images?.jpg?.let { it.image_url?.let { it1 -> binding.imageView2.glideImageSet(it1) } }

            // Setzt die Textansichten für Typ und Quelle, verwendet Ressourcenstrings für die Formatierung.
            binding.textView2.text =
                itemView.context.getString(R.string.type) + item.type + "\n" + itemView.context.getString(R.string.source) + (item.source)

            // Setzt den Titel des Anime.
            binding.textView3.text = item.title

            // Klick-Listener, der den Benutzer zur Detailansicht des ausgewählten Anime führt.
            itemView.setOnClickListener { view ->
                val action = item.mal_id?.let {
                    RankingFragmentDirections.actionRankingFragmentToAnimeDetailFragment(
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
