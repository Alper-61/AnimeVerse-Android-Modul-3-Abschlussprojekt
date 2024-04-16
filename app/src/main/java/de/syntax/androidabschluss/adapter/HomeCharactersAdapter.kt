// Paketdeklaration und Importanweisungen für notwendige Klassen und Bibliotheken.
package de.syntax.androidabschluss.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.data.models.CharactersData
import de.syntax.androidabschluss.databinding.DesignHomeAnimeCharactersBinding
import de.syntax.androidabschluss.ui.HomeFragmentDirections

// Adapterklasse für einen RecyclerView, der eine Liste von Charakteren anzeigt.
class HomeCharactersAdapter(private val dataset: List<CharactersData>) :
    RecyclerView.Adapter<HomeCharactersAdapter.HomeCharacterViewHolder>() {

    // ViewHolder-Klasse, die die Bindungen für das Layout der Charaktere enthält.
    inner class HomeCharacterViewHolder(val binding: DesignHomeAnimeCharactersBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Erstellt neue Ansichten (aufgerufen vom Layout-Manager).
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCharacterViewHolder {
        val binding = DesignHomeAnimeCharactersBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return HomeCharacterViewHolder(binding)
    }

    // Gibt die Anzahl der Elemente im Datensatz zurück, also wie viele Elemente im RecyclerView angezeigt werden.
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Verknüpft die Daten mit einer Ansicht (aufgerufen vom Layout-Manager).
    override fun onBindViewHolder(holder: HomeCharacterViewHolder, position: Int) {
        val item = dataset[position]
        holder.apply {
            // Setzt das Bild des Charakters mithilfe von Glide, wenn eine Bild-URL vorhanden ist.
            item.images?.jpg?.let { binding.imageView.glideImageSet(it.image_url) }

            // Setzt den Namen des Charakters.
            binding.textView.text = item.name

            // Klick-Listener, der den Benutzer zur Detailansicht des ausgewählten Charakters führt.
            itemView.setOnClickListener { view ->
                val action = item.mal_id?.let {
                    HomeFragmentDirections.actionHomeFragmentToCharacterDetailFragment(it)
                }
                if (action != null) {
                    view.findNavController().navigate(action)
                }
            }
        }
    }
}
