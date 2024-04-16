package de.syntax.androidabschluss.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import de.syntax.androidabschluss.adapter.FavoriteAnimeAdapter
import de.syntax.androidabschluss.adapter.FavoriteCharactersAdapter
import de.syntax.androidabschluss.data.local.EntityAnime
import de.syntax.androidabschluss.data.local.EntityCharacters
import de.syntax.androidabschluss.databinding.FragmentFavoriteBinding
import de.syntax.androidabschluss.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


// Fragment zur Anzeige von Lieblingsanimes und -charakteren.
class FavoriteFragment : Fragment() {
    private lateinit var binding : FragmentFavoriteBinding
    private val viewModel: MainViewModel by activityViewModels()



    private var listAnime: ArrayList<EntityAnime> = ArrayList()
    private lateinit var adapterAnime : FavoriteAnimeAdapter

    private var listCharacters : ArrayList<EntityCharacters> = ArrayList()
    private lateinit var adapterCharacters : FavoriteCharactersAdapter

    // Erstellt und initialisiert die Ansicht des Fragments.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)

        return binding.root
    }


    // Wird aufgerufen, sobald die Ansicht erstellt wurde.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            rvAnime.setHasFixedSize(true)
            val lmAnime = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            rvAnime.layoutManager = lmAnime
            adapterAnime = FavoriteAnimeAdapter(listAnime)
            rvAnime.adapter = adapterAnime


            rvCharacters.setHasFixedSize(true)
            val lmCha = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            rvCharacters.layoutManager = lmCha
            adapterCharacters = FavoriteCharactersAdapter(listCharacters)
            rvCharacters.adapter = adapterCharacters
        }


        getData()
        viewModel.getFavoriteAnime()
        viewModel.getFavoriteCharacters()
    }


    // Lädt die Daten für Anime und Charaktere und aktualisiert die Ansichten.
    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        /**
         * Nach dem Abrufen der Favoriten aus dem Viewmodel wurde die interne Funktion verwendet, nachdem alle Favoritenlisten abgerufen wurden.
         * Wenn die Listen leer sind, gibt es keine Favoriten, wenn die Listen voll sind, werden die Favoriten angezeigt*/
        fun nestedFunc() {
            viewModel.dbData.observe(viewLifecycleOwner) {
                // Aktualisiert die Liste der Charakter-Favoriten.
                CoroutineScope(Dispatchers.IO).launch{
                    withContext(Dispatchers.Main) {
                        listCharacters.clear()
                        if (it.isNotEmpty()) {
                            listCharacters.addAll(it)
                            adapterCharacters.notifyDataSetChanged()
                            binding.chaTv.visibility = View.VISIBLE
                            binding.rvCharacters.visibility = View.VISIBLE
                        }else {
                            binding.chaTv.visibility = View.GONE
                            binding.rvCharacters.visibility = View.GONE
                        }

                        if (listCharacters.isNotEmpty() || listAnime.isNotEmpty()) {
                            binding.favoriteemptytv.visibility = View.GONE
                            binding.scrollView.visibility = View.VISIBLE
                        }else {
                            binding.favoriteemptytv.visibility = View.VISIBLE
                            binding.scrollView.visibility = View.GONE
                        }
                    }
                }
            }
        }
        viewModel.animeDbData.observe(viewLifecycleOwner){

            // Aktualisiert die Liste der Anime-Favoriten.
            CoroutineScope(Dispatchers.IO).launch{
                withContext(Dispatchers.Main) {
                    listAnime.clear()
                    if (it.isNotEmpty()) {
                        listAnime.addAll(it)
                        adapterAnime.notifyDataSetChanged()
                        binding.animeTv.visibility = View.VISIBLE
                        binding.rvAnime.visibility = View.VISIBLE
                    }else {
                        binding.animeTv.visibility = View.GONE
                        binding.rvAnime.visibility = View.GONE
                    }
                    nestedFunc()
                }
            }



        }


    }


}
