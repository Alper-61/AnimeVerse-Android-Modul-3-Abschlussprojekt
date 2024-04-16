package de.syntax.androidabschluss.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import de.syntax.androidabschluss.data.models.TopReviewsData
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.databinding.FragmentTopReviewsDetailBinding


// Detailansicht für Top-Rezensionen.
class TopReviewsDetailFragment : Fragment() {

    private var binding: FragmentTopReviewsDetailBinding? = null // Bindung zur Layout-Datei für das Fragment.
    private val args: TopReviewsDetailFragmentArgs by navArgs() // Zugriff auf die übergebenen Argumente.

    // Erstellt die Ansicht des Fragments und initialisiert das Binding.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopReviewsDetailBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    // Wird aufgerufen, sobald die Ansicht erstellt wurde.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(args.list)// Setzt die Detailansicht mit den übergebenen Daten.
        binding?.backBtn?.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()// Fügt dem Zurück-Button eine Funktion zu.
        }


        }

    // Konfiguriert die Ansicht basierend auf den übergebenen Rezensionsdaten.
    private fun setupView(reviewData: TopReviewsData) {
        binding?.titleTv?.text = reviewData.entry?.title // Setzt den Titel der Rezension.
        binding?.ratingTv?.text = "${getString(R.string.score)} ${reviewData.score}${getString(R.string.ten)}" // Setzt die Bewertung.
        reviewData.entry?.images?.jpg?.image_url?.let { imageUrl ->
            binding?.animImage?.glideImageSet(imageUrl) // Lädt und zeigt das Bild zur Rezension.
        }
        binding?.typeTv?.text = "${getString(R.string.type)} ${reviewData.type}" // Setzt den Typ der Rezension.
        binding?.descriptionTv?.text = reviewData.review // Setzt den Text der Rezension.
    }

    // Bereinigt das Binding, wenn die Ansicht zerstört wird, um Speicherlecks zu vermeiden.
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
