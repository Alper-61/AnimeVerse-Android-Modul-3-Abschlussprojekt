package de.syntax.androidabschluss.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.adapter.RankingTopCharactersAdapter
import de.syntax.androidabschluss.data.models.CharactersData
import de.syntax.androidabschluss.databinding.FragmentTopCharactersBinding
import de.syntax.androidabschluss.viewmodel.MainViewModel


// Fragment zur Anzeige einer Liste der Top-Characters.
class TopCharactersFragment : Fragment() {
    private lateinit var binding : FragmentTopCharactersBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private var listTCha : ArrayList<CharactersData> = ArrayList()
    private lateinit var adapterTCha : RankingTopCharactersAdapter

    // Erstellt die Ansicht des Fragments und initialisiert das Binding.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopCharactersBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    // Konfiguriert das Verhalten und die Inhalte der Ansicht, nachdem diese erstellt wurde.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvTopCharacters.setHasFixedSize(true)
            val lm = LinearLayoutManager(requireContext())
            rvTopCharacters.layoutManager = lm
            adapterTCha = RankingTopCharactersAdapter(listTCha)
            rvTopCharacters.adapter = adapterTCha

        }
        getListener()
        viewModel.getTopCharactersList(FirstPage)// Lädt die erste Seite der Top-Characters.
        paginationFunc()// Setzt die Paginierungsfunktion auf.
    }

    private var TotalPage = 1
    private var FirstPage = 1
    private var PaginationDuration = false

    // Implementiert die Logik für das unendliche Scrollen/Paginieren.
    private fun paginationFunc() {
        binding.rvTopCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                    if (FirstPage < TotalPage) {
                        if (!PaginationDuration) {
                            PaginationDuration = true
                            FirstPage += 1
                            viewModel.getTopCharactersList(FirstPage)
                        }

                    }
                }
            }
        })
    }

    // Setzt den Listener, der auf Änderungen der Daten reagiert und den Adapter aktualisiert.
    @SuppressLint("NotifyDataSetChanged")
    private fun getListener() {
        viewModel.characters.observe(viewLifecycleOwner) {model->
            listTCha.clear()
            TotalPage = model.pagination?.totalPage ?: 1
            model.data?.let { it1 -> listTCha.addAll(it1) }
            adapterTCha.notifyDataSetChanged()
            PaginationDuration = false
        }

    }
}
