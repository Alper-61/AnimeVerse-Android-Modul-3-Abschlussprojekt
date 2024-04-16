package de.syntax.androidabschluss.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.data.models.CharactersData
import de.syntax.androidabschluss.databinding.DesignHomeTopAnimeCharactersBinding
import de.syntax.androidabschluss.ui.Utils.RankingFragmentDirections

// Adapterklasse für einen RecyclerView, der eine Liste der top-bewerteten Charaktere anzeigt.
class RankingTopCharactersAdapter(private val dataset: List<CharactersData>) :
    RecyclerView.Adapter<RankingTopCharactersAdapter.RankingTopViewHolder>() {

    // ViewHolder-Klasse, die die Bindungen für das spezifische Layout der top-bewerteten Charaktere enthält.
    inner class RankingTopViewHolder(val binding: DesignHomeTopAnimeCharactersBinding) : RecyclerView.ViewHolder(binding.root)

    // Erstellt neue Ansichten (aufgerufen vom Layout-Manager).
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingTopViewHolder {
        val binding = DesignHomeTopAnimeCharactersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RankingTopViewHolder(binding)
    }

    // Gibt die Anzahl der Elemente in der Liste zurück, also wie viele Elemente im RecyclerView angezeigt werden.
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Verknüpft die Daten mit einer Ansicht (aufgerufen vom Layout-Manager).
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RankingTopViewHolder, position: Int) {
        val item = dataset[position]
        holder.apply {
            // Setzt das Bild des Charakters mithilfe von Glide, wenn eine Bild-URL vorhanden ist.
            item.images?.jpg?.let { it.image_url.let { it1 -> binding.imageView2.glideImageSet(it1) } }

            // Setzt den Namen des Charakters mit einem Präfix "Name: ".
            binding.textView2.text = itemView.context.getString(R.string.name) + item.name

            // Setzt die Beschreibung des Charakters.
            binding.textView3.text = item.about

            // Klick-Listener, der den Benutzer zur Detailansicht des ausgewählten Charakters führt.
            itemView.setOnClickListener { view ->
                val action = item.mal_id?.let {
                    RankingFragmentDirections.actionRankingFragmentToCharacterDetailFragment(it)
                }
                if (action != null) {
                    view.findNavController().navigate(action)
                }
            }
        }
    }
}
