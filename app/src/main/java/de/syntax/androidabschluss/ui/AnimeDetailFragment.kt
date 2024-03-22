package de.syntax.androidabschluss.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.task.animeinfo.View.Fragment.AnimeDetailFragmentArgs
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.data.models.AnimeDetailModel
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.data.remote.AnimeApiService
import de.syntax.androidabschluss.databinding.FragmentAnimeDetailBinding
import de.syntax.androidabschluss.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AnimeDetailFragment : Fragment() {
    private var _binding: FragmentAnimeDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private val args: AnimeDetailFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAnimeDetails(args.malId)


        viewModel.animeDetailData.observe(viewLifecycleOwner) { animeDetail ->
            updateUI(animeDetail)
        }





        binding.backBtn.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    private fun updateUI(animeDetail: AnimeDetailModel?) {
        animeDetail?.let {
            binding.titleTv.text = it.data.title
            binding.ratingTv.text = getString(R.string.rating)
            binding.typeTv.text = getString(R.string.type,)
            binding.sourceTv.text = getString(R.string.source)
            binding.epidoseTv.text = getString(R.string.epidose)+it.data.episodes
            binding.durationTv.text = getString(R.string.duration)+it.data.duration
            binding.sourceTv.text = getString(R.string.source) + it.data.source + getString(R.string.ten)
            binding.historyTv.text = it.data.history
            binding.descriptionTv.text = it.data.description

            CoroutineScope(Dispatchers.Main).launch {
                binding.animImage.glideImageSet(it.data.images?.jpg?.image_url.toString())
            }


        }
    }

    private fun updateFavoriteButton(isFavorite: Boolean) {
        if (isFavorite) {
            binding.favoriteBtn.setImageResource(R.drawable.favoritefill)
        } else {
            binding.favoriteBtn.setImageResource(R.drawable.favoriteempty)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
