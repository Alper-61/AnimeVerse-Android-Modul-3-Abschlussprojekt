package de.syntax.androidabschluss.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.adapter.RankingTopCharactersAdapter
import de.syntax.androidabschluss.data.models.CharactersData
import de.syntax.androidabschluss.databinding.FragmentTopCharactersBinding
import de.syntax.androidabschluss.viewmodel.MainViewModel

class TopCharactersFragment : Fragment() {
    private lateinit var b : FragmentTopCharactersBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private var listTCha : ArrayList<CharactersData> = ArrayList()
    private lateinit var adapterTCha : RankingTopCharactersAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentTopCharactersBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b.apply {
            rvTopCharacters.setHasFixedSize(true)
            val lm = LinearLayoutManager(requireContext())
            rvTopCharacters.layoutManager = lm
            adapterTCha = RankingTopCharactersAdapter(listTCha)
            rvTopCharacters.adapter = adapterTCha

        }
        getListener()
        viewModel.getTopCharactersList(FirstPage)
        paginationFunc()
    }

    private var TotalPage = 1
    private var FirstPage = 1
    private var PaginationDuration = false
    private fun paginationFunc() {
        b.rvTopCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
