package de.syntax.androidabschluss.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.task.animeinfo.View.Fragment.CharacterDetailFragmentArgs
import de.syntax.androidabschluss.CharacterDetailModel
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.databinding.FragmentCharacterDetailBinding
import de.syntax.androidabschluss.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope

class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    // Ersetzt durch das zentrale MainViewModel
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        val id = CharacterDetailFragmentArgs.fromBundle(requireArguments()).malId

        observeCharacterDetails(id)
        /*observeFavoriteStatus(id)
        setupFavoriteButton(id)*/

        return binding.root
    }

    private fun observeCharacterDetails(id: Int) {
        viewModel.getCharacterDetails(id)
        viewModel.characterDetailData.observe(viewLifecycleOwner) { character ->
            updateUI(character)
        }
    }

    /*private fun observeFavoriteStatus(id: Int) {
        viewModel.getFavoriteStatus(id) // Angenommen, diese Methode existiert im MainViewModel
        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            updateFavoriteButton(isFavorite)
        }
    }*/

    /*private fun setupFavoriteButton(id: Int) {
        binding.favoriteBtn.setOnClickListener {
            viewModel.toggleFavoriteCharacter(id) // Angenommen, diese Methode existiert im MainViewModel
        }
    }*/

    private fun updateUI(character: CharacterDetailModel?) {
        // Aktualisiere UI-Elemente basierend auf dem CharacterDetailModel
        character?.let {
            binding.titleTv.text = it.data.name
            binding.descriptionTv.text = it.data.about
            CoroutineScope(Dispatchers.Main).launch {
                val image = it.data.images.toString() // imageUrl sollte Teil deines CharacterDetailModels sein
                binding.animImage.glideImageSet(it.data.url.toString())
            }
        }
    }

    /*private fun updateFavoriteButton(isFavorite: Boolean) {
        binding.favoriteBtn.setImageResource(if (isFavorite) R.drawable.favorite_fill else R.drawable.favorite_empty)
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
