package de.syntax.androidabschluss.ui

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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

class CharacterDetailFragment : Fragment() {
    private var b: FragmentCharacterDetailBinding? = null
    private lateinit var viewModel : MainViewModel

    private val viewModelFavorite: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentCharacterDetailBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        return b?.root
    }
    private var malId = 0
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

    private fun setup() {
        b?.apply {
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
    private var detailsItem: TempEntityCharacters =
        TempEntityCharacters(0, "", "", "")
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
                b?.apply {
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
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getFavorite() {
        viewModelFavorite.insertStatus.observe(viewLifecycleOwner) {
            if (it != null) {
                b?.favoriteBtn?.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.favoritefill,
                        null
                    )
                )
            }else {
                b?.favoriteBtn?.setImageDrawable(
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
                        b?.favoriteBtn?.setImageDrawable(
                            resources.getDrawable(
                                R.drawable.favoritefill,
                                null
                            )
                        )
                    } else {
                        b?.favoriteBtn?.setImageDrawable(
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

    override fun onDestroyView() {
        super.onDestroyView()
        b = null // Verhindern von Memory Leaks
    }


}