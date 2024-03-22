package de.syntax.androidabschluss.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import de.syntax.androidabschluss.adapter.FavoriteAnimeAdapter
import de.syntax.androidabschluss.adapter.FavoriteCharactersAdapter
import de.syntax.androidabschluss.databinding.FragmentFavoriteBinding
import de.syntax.androidabschluss.viewmodel.MainViewModel

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapterAnime: FavoriteAnimeAdapter
    private lateinit var adapterCharacters: FavoriteCharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        /*setupRecyclerViews()
        observeFavoriteAnimes()
        observeFavoriteCharacters()*/

        /*viewModel.getFavoriteAnimes()
        viewModel.getFavoriteCharacters()*/

        return binding.root
    }

    private fun setupRecyclerViews() {
        adapterAnime = FavoriteAnimeAdapter(ArrayList())
        binding.rvAnime.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            adapter = adapterAnime
        }

        adapterCharacters = FavoriteCharactersAdapter(ArrayList())
        binding.rvCharacters.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            adapter = adapterCharacters
        }
    }

    /*private fun observeFavoriteAnimes() {
        viewModel.favoriteAnimes.observe(viewLifecycleOwner) { animes ->
            adapterAnime.updateData(animes)
            binding.animeTv.visibility = if (animes.isNotEmpty()) View.VISIBLE else View.GONE
            binding.rvAnime.visibility = if (animes.isNotEmpty()) View.VISIBLE else View.GONE
            updateEmptyState()
        }
    }

    private fun observeFavoriteCharacters() {
        viewModel.favoriteCharacters.observe(viewLifecycleOwner) { characters ->
            adapterCharacters.updateData(characters)
            binding.chaTv.visibility = if (characters.isNotEmpty()) View.VISIBLE else View.GONE
            binding.rvCharacters.visibility = if (characters.isNotEmpty()) View.VISIBLE else View.GONE
            updateEmptyState()
        }
    }*/

    private fun updateEmptyState() {
        val showEmptyState = adapterAnime.itemCount == 0 && adapterCharacters.itemCount == 0
        binding.favoriteemptytv.visibility = if (showEmptyState) View.VISIBLE else View.GONE
        binding.scrollView.visibility = if (!showEmptyState) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
