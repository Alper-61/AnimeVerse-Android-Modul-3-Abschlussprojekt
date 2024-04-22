package de.syntax.androidabschluss.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.data.local.EntityCharacters
import de.syntax.androidabschluss.data.local.TempEntityCharacters
import de.syntax.androidabschluss.databinding.FragmentCharacterDetailBinding
import de.syntax.androidabschluss.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext


// Fragment zur Detailansicht eines Charakters.
class CharacterDetailFragment : Fragment() {
    private var binding: FragmentCharacterDetailBinding? = null
    private lateinit var viewModel : MainViewModel

    // Verwendung des ViewModel aus dem umgebenden Aktivitätskontext.
    private val viewModelFavorite: MainViewModel by activityViewModels()


    // Erstellt und initialisiert die Ansicht des Fragments.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        return binding?.root
    }
    private var malId = 0

    // Wird aufgerufen, sobald die Ansicht erstellt wurde.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle:CharacterDetailFragmentArgs by navArgs()
        malId = bundle.malId
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setup()
        getData()
        getFavorite()
        viewModel.getCharacterDetails(malId)
        viewModelFavorite.searchFavoriteCharacter(malId)

    }

    // Initialisiert UI-Elemente und Interaktionen.
    private fun setup() {
        binding?.apply {
            favoriteBtn.setOnClickListener {
                if (detailsItem.image_url.isNotEmpty()) {
                    viewModelFavorite.addFavoriteCharacter(
                        EntityCharacters(detailsItem.mal_id,detailsItem.image_url,detailsItem.name,detailsItem.about)
                    )
                }
            }
            backBtn.setOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }
    }

    // Temporäres Modell zur Speicherung von Charakterdetails.
    private var detailsItem: TempEntityCharacters =
        TempEntityCharacters(0, "", "", "")

    // Lädt die Daten des Charakters und stellt sie dar.
    @SuppressLint("SetTextI18n")
    private fun getData() {
        viewModel.characterDetailData.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it.data.mal_id != null) {
                    detailsItem.mal_id = it.data.mal_id
                    detailsItem.image_url = it.data.images?.jpg?.image_url!!
                    detailsItem.name = it.data.name!!
                    detailsItem.about = it.data.about!!

                }
                binding?.apply {
                    CoroutineScope(Dispatchers.Main).launch {
                        var image = it.data.images?.jpg?.image_url
                        if (image != null) {
                            animImage.glideImageSet(image)
                        }
                        titleTv.text = it.data.name
                        descriptionTv.text = it.data.about

                    }
                }
            }else {
                viewModel.getCharacterDetails(malId)
            }




        }
    }
    private var addedFavorite: Boolean = false

    // Verwaltet den Favoritenstatus und das Icon für den Favoritenbutton.
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getFavorite() {
        viewModelFavorite.insertStatus.observe(viewLifecycleOwner) {
            if (it != null) {
                binding?.favoriteBtn?.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.favoritefill,
                        null
                    )
                )
            }else {
                binding?.favoriteBtn?.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.favoriteempty,
                        null
                    )
                )
            }
        }
        viewModelFavorite.dbSearchStatus.observe(viewLifecycleOwner) { entityAnime ->
            addedFavorite = entityAnime != null
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    //Favorite Icon Fill || Empty transactions
                    if (addedFavorite) {
                        binding?.favoriteBtn?.setImageDrawable(
                            resources.getDrawable(
                                R.drawable.favoritefill,
                                null
                            )
                        )
                    } else {
                        binding?.favoriteBtn?.setImageDrawable(
                            resources.getDrawable(
                                R.drawable.favoriteempty,
                                null
                            )
                        )
                    }
                }
            }
        }
    }

    // Bereinigt die Bindungen, wenn das Fragment zerstört wird, um Speicherlecks zu verhindern.
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null // Verhindern von Memory Leaks
    }


}