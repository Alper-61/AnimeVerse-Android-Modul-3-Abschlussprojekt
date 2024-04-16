package de.syntax.androidabschluss.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.data.models.TopReviewsData
import de.syntax.androidabschluss.databinding.DesignHomeTopreviewsBinding
import de.syntax.androidabschluss.ui.HomeFragmentDirections

// Adapterklasse für einen RecyclerView, der eine Liste von Top-Rezensionen anzeigt.
class HomeTopReviewsAdapter(private val dataset: List<TopReviewsData>) :
    RecyclerView.Adapter<HomeTopReviewsAdapter.HomeTopReviewsViewHolder>() {

    // ViewHolder-Klasse, die die Bindungen für das spezifische Layout der Top-Rezensionen enthält.
    inner class HomeTopReviewsViewHolder(val binding: DesignHomeTopreviewsBinding) : RecyclerView.ViewHolder(binding.root)

    // Erstellt neue Ansichten (aufgerufen vom Layout-Manager).
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTopReviewsViewHolder {
        val binding = DesignHomeTopreviewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return HomeTopReviewsViewHolder(binding)
    }

    // Gibt die Anzahl der Elemente im Datensatz zurück, also wie viele Elemente im RecyclerView angezeigt werden.
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Verknüpft die Daten mit einer Ansicht (aufgerufen vom Layout-Manager).
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HomeTopReviewsViewHolder, position: Int) {
        val item = dataset[position]
        holder.apply {
            // Setzt das Bild des Bewerteten Objekts mithilfe von Glide, wenn eine Bild-URL vorhanden ist.
            item.entry?.images?.jpg?.let { binding.imageView2.glideImageSet(it.image_url) }

            // Setzt die Textansichten für Typ und Titel, verwendet Ressourcenstrings für die Formatierung.
            binding.textView2.text =
                itemView.context.getString(R.string.type) + item.type + "\n" + itemView.context.getString(
                    R.string.title
                ) + (item.entry?.title ?: itemView.context.getString(R.string.unknown))

            // Setzt den Text der Rezension.
            binding.textView3.text = item.review

            // Klick-Listener, der den Benutzer zur Detailansicht der ausgewählten Top-Rezension führt.
            itemView.setOnClickListener { view ->
                val action = HomeFragmentDirections.actionHomeFragmentToTopReviewsDetailFragment(item)
                view.findNavController().navigate(action)
            }
        }
    }
}
