package de.syntax.androidabschluss.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import de.syntax.androidabschluss.adapter.RankingTopAnimeAdapter
import de.syntax.androidabschluss.data.models.AnimeData
import de.syntax.androidabschluss.databinding.FragmentTopAnimeBinding
import de.syntax.androidabschluss.viewmodel.MainViewModel

class TopAnimeFragment : Fragment() {

    private var _binding: FragmentTopAnimeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var adapterTAnime: RankingTopAnimeAdapter
    private var listTAnime: ArrayList<AnimeData> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopAnimeBinding.inflate(inflater, container, false)
        setupRecyclerView()
        observeViewModel()
        viewModel.getTopAnimeList(1) // Initialer Aufruf mit Seite 1
        return binding.root
    }

    private fun setupRecyclerView() {
        adapterTAnime = RankingTopAnimeAdapter(listTAnime)
        binding.rvTAnime.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterTAnime
        }
    }

    private fun observeViewModel() {
        viewModel.topreviews.observe(viewLifecycleOwner) { animeList ->
            listTAnime.clear()
            animeList?.let { list ->
                listTAnime.addAll(listTAnime)
                adapterTAnime.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
