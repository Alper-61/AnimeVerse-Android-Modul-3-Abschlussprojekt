package de.syntax.androidabschluss.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.data.local.EntityAnime
import de.syntax.androidabschluss.data.local.TempEntityAnimeModel
import de.syntax.androidabschluss.databinding.FragmentAnimeDetailBinding
import de.syntax.androidabschluss.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AnimeDetailFragment : Fragment() {
    private lateinit var binding: FragmentAnimeDetailBinding
    private lateinit var viewModel: MainViewModel

    private val viewModelFavorite: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimeDetailBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle: AnimeDetailFragmentArgs by navArgs()
        val id = bundle.malId

        setup()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        getData()
        viewModel.getAnimeDetails(id)


        getFavorite()
        viewModelFavorite.searchFavoriteAnime(id)
        }


    private fun setup() {
        binding.apply {
            favoriteBtn.setOnClickListener {
                if (detailsItem.image_url.isNotEmpty()) {
                    viewModelFavorite.addFavoriteAnime(
                        EntityAnime(
                            detailsItem.mal_id,
                            detailsItem.image_url,
                            detailsItem.title,
                            detailsItem.type,
                            detailsItem.source
                        )
                    )
                }

            }

            backBtn.setOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }
    }

    //Favori Btn da kullanmak için
    private var detailsItem: TempEntityAnimeModel =
        TempEntityAnimeModel(0, "", "", "", "")

    @SuppressLint("SetTextI18n")
    private fun getData() {
        viewModel.animeDetailData.observe(viewLifecycleOwner) {
            if (it.data.mal_id != null) {
                detailsItem.mal_id = it.data.mal_id
                detailsItem.image_url = it.data.images?.jpg?.image_url!!
                detailsItem.title = it.data.title!!
                detailsItem.type = it.data.type!!
                detailsItem.source = it.data.source!!

            }

            binding.apply {
                CoroutineScope(Dispatchers.Main).launch {
                    var image = it.data.images?.jpg?.large_image_url
                    if (image != null) {
                        animImage.glideImageSet(image)
                    } else {
                        image = it.data.images?.jpg?.image_url
                        if (image != null) {
                            animImage.glideImageSet(image)
                        } else {
                            image = it.data.images?.jpg?.small_image_url
                            if (image != null) {
                                animImage.glideImageSet(image)
                            }
                        }

                    }
                    titleTv.text = it.data.title
                    ratingTv.text = getString(R.string.rating) + it.data.rating
                    typeTv.text = getString(R.string.type) + it.data.type
                    sourceTv.text = getString(R.string.source) + it.data.source
                    epidoseTv.text = getString(R.string.epidose) + it.data.episodes
                    durationTv.text = getString(R.string.duration) + it.data.duration
                    scoreTv.text =
                        getString(R.string.score) + it.data.score + getString(R.string.ten)
                    historyTv.text = it.data.history
                    descriptionTv.text = it.data.description

                }
            }

        }
    }



    private var addedFavorite: Boolean = false

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getFavorite() {
        viewModelFavorite.insertStatus.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.favoriteBtn.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.favoritefill,
                        null
                    )
                )
            } else {
                binding.favoriteBtn.setImageDrawable(
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
                        binding.favoriteBtn.setImageDrawable(
                            resources.getDrawable(
                                R.drawable.favoritefill,
                                null
                            )
                        )
                    } else {
                        binding.favoriteBtn.setImageDrawable(
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
        binding = null!! // Verhindern von Memory Leaks
    }

}