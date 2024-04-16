package de.syntax.androidabschluss.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import de.syntax.androidabschluss.adapter.HomeAnimeAdapter
import de.syntax.androidabschluss.adapter.HomeCharactersAdapter
import de.syntax.androidabschluss.adapter.HomeTopReviewsAdapter
import de.syntax.androidabschluss.data.models.AnimeData
import de.syntax.androidabschluss.data.models.CharactersData
import de.syntax.androidabschluss.data.models.TopReviewsData
import de.syntax.androidabschluss.databinding.FragmentHomeBinding
import de.syntax.androidabschluss.viewmodel.MainViewModel
import kotlinx.coroutines.joinAll

class HomeFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding

    private var listAnime : ArrayList<AnimeData> = ArrayList()
    private lateinit var adapterAnime : HomeAnimeAdapter

    private var listCharacters : ArrayList<CharactersData> = ArrayList()
    private lateinit var adapterCharacters : HomeCharactersAdapter

    private var listTopReviews : ArrayList<TopReviewsData> = ArrayList()
    private lateinit var adapterTopReviews : HomeTopReviewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getListener()
        paginationFunc()
        viewModel.getAnimeList(animeFirstPage)
        viewModel.getCharactersList(chaFirstPage)
        viewModel.getTopReviewsList()
            binding.rvAnime.setHasFixedSize(true)
            val lmAnime = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            binding.rvAnime.layoutManager = lmAnime
            adapterAnime = HomeAnimeAdapter(listAnime)
            binding.rvAnime.adapter = adapterAnime


            binding.rvCharacters.setHasFixedSize(true)
            val lmCha = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            binding.rvCharacters.layoutManager = lmCha
            adapterCharacters = HomeCharactersAdapter(listCharacters)
            binding.rvCharacters.adapter = adapterCharacters

            binding.rvTopReviews.setHasFixedSize(true)
            val lmTr = LinearLayoutManager(requireContext())
            binding.rvTopReviews.layoutManager = lmTr
            adapterTopReviews = HomeTopReviewsAdapter(listTopReviews)
            binding.rvTopReviews.adapter = adapterTopReviews

        }




    private fun paginationFunc() {
        binding.rvAnime.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager

                val visibleItems = layoutManager.childCount
                val totalItems = layoutManager.itemCount
                val firstVisibleItemPositions = layoutManager.findFirstVisibleItemPositions(null)

                if (firstVisibleItemPositions.any { it + visibleItems >= totalItems }) {
                    if (animeFirstPage < animeTotalPage) {
                        if (!animePaginationDuration) {
                            animePaginationDuration = true
                            animeFirstPage += 1
                            viewModel.getAnimeList(animeFirstPage)
                        }

                    }

                }
            }
        })

        binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager

                val visibleItems = layoutManager.childCount
                val totalItems = layoutManager.itemCount
                val firstVisibleItemPositions = layoutManager.findFirstVisibleItemPositions(null)

                if (firstVisibleItemPositions.any { it + visibleItems >= totalItems }) {
                    if (chaFirstPage < chaTotalPage) {
                        if (!chaPaginationDuration) {
                            chaPaginationDuration = true
                            chaFirstPage += 1
                            viewModel.getCharactersList(chaFirstPage)
                        }

                    }

                }
            }
        })

    }

    private var animeTotalPage = 1
    private var animeFirstPage = 1
    private var animePaginationDuration = false

    private var chaTotalPage = 1
    private var chaFirstPage = 1
    private var chaPaginationDuration = false



    @SuppressLint("NotifyDataSetChanged")
    private fun getListener() {
        listAnime.clear()
        listCharacters.clear()
        listTopReviews.clear()
        viewModel.anime.observe(viewLifecycleOwner) {model->
            try {
                animeTotalPage = model.pagination?.totalPage ?: 1
                model.data?.let { it1 ->
                    listAnime.clear()
                    listAnime.addAll(it1) }
                adapterAnime.notifyDataSetChanged()
                animePaginationDuration = false
            }catch (e:Exception) {

            }


        }

        viewModel.characters.observe(viewLifecycleOwner) {model->
            try {
                chaTotalPage = model.pagination?.totalPage ?: 1
                model.data?.let { it1 ->
                    listCharacters.clear()
                    listCharacters.addAll(it1) }
                adapterCharacters.notifyDataSetChanged()
                chaPaginationDuration = false
            }catch (e:Exception) {

            }

        }

        viewModel.topreviews.observe(viewLifecycleOwner) {model->
            try {
                model.data?.let { it1 ->
                    listTopReviews.clear()
                    listTopReviews.addAll(it1) }
                adapterTopReviews.notifyDataSetChanged()
            }catch (e:Exception) {

            }

        }
    }
}



