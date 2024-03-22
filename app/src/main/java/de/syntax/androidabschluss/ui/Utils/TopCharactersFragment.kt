package de.syntax.androidabschluss.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import de.syntax.androidabschluss.adapter.RankingTopCharactersAdapter
import de.syntax.androidabschluss.data.models.CharactersData
import de.syntax.androidabschluss.databinding.FragmentTopCharactersBinding
import de.syntax.androidabschluss.viewmodel.MainViewModel

class TopCharactersFragment : Fragment() {

    private var _binding: FragmentTopCharactersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var adapterTCha: RankingTopCharactersAdapter
    private var listTCha: ArrayList<CharactersData> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopCharactersBinding.inflate(inflater, container, false)
        setupRecyclerView()
        observeViewModel()
        viewModel.getTopCharactersList(1) // Initialer Aufruf mit Seite 1
        return binding.root
    }

    private fun setupRecyclerView() {
        adapterTCha = RankingTopCharactersAdapter(listTCha)
        binding.rvTopCharacters.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterTCha
        }
    }

    private fun observeViewModel() {
        viewModel.characters.observe(viewLifecycleOwner) { charactersList ->
            listTCha.clear()
            charactersList?.let { list ->
                listTCha.addAll(listTCha)
                adapterTCha.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
