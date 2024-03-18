package de.syntax.androidabschluss.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import de.syntax.androidabschluss.AnimeData
import de.syntax.androidabschluss.AnimeDetailModel
import de.syntax.androidabschluss.AnimeModel
import de.syntax.androidabschluss.CharacterDetailModel
import de.syntax.androidabschluss.CharactersModel
import de.syntax.androidabschluss.HomeTopReviewsModel
import de.syntax.androidabschluss.data.Repository
import de.syntax.androidabschluss.data.models.*
import de.syntax.androidabschluss.data.remote.AnimeApi
import de.syntax.androidabschluss.data.local.AppDatabase
import de.syntax.androidabschluss.data.local.AppDatabaseCharacters
import de.syntax.androidabschluss.data.local.Dao
import de.syntax.androidabschluss.data.local.EntityAnime
import de.syntax.androidabschluss.data.local.EntityCharacters
import de.syntax.androidabschluss.data.remote.AnimeApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(
        AnimeApi.retrofitService,
        AppDatabase.getInstance(application),
        AppDatabaseCharacters.getInstance(application)

    )


    private val _animeDetailData = MutableLiveData<AnimeDetailModel>()
    val animeDetailData : LiveData<AnimeDetailModel> get() = _animeDetailData

    private val _characterDetailData = MutableLiveData<CharacterDetailModel>()
    val characterDetailData : LiveData<CharacterDetailModel> get() = _characterDetailData

    private val _animeDbData = MutableLiveData<List<EntityAnime>>()
    val animeDbData : LiveData<List<EntityAnime>> get() = _animeDbData

    private val _animeDbSearch = MutableLiveData<EntityAnime?>()
    val animeDbSearchStatus : LiveData<EntityAnime?> get() = _animeDbSearch

    private val _animeInsertStatus = MutableLiveData<Long?>()
    val animeInsertStatus : LiveData<Long?> get() = _insertStatus

    private val _dbData = MutableLiveData<List<EntityCharacters>>()
    val dbData : LiveData<List<EntityCharacters>> get() = _dbData

    private val _dbSearch = MutableLiveData<EntityCharacters?>()
    val dbSearchStatus : LiveData<EntityCharacters?> get() = _dbSearch

    private val _insertStatus = MutableLiveData<Long?>()
    val insertStatus : LiveData<Long?> get() = _insertStatus

    private val _anime = MutableLiveData<AnimeModel>()
    val anime : LiveData<AnimeModel> get() = _anime

    private val _characters = MutableLiveData<CharactersModel>()
    val characters : LiveData<CharactersModel> get() = _characters

    private val _topreviews = MutableLiveData<HomeTopReviewsModel>()
    val topreviews : LiveData<HomeTopReviewsModel> get() = _topreviews






    fun getAnimeList(page:Int) {
        viewModelScope.launch {
            _anime.postValue(repository.getAnimeList(page))
        }
    }

    fun getCharactersList(page: Int) {
        viewModelScope.launch {
            _characters.postValue(repository.getCharactersList(page))
        }
    }

    fun getAnimeDetails(mal_id: Int){
        viewModelScope.launch {
            _animeDetailData.postValue(repository.getAnimeDetail(mal_id))
        }
    }

    fun getCharacterDetails(mal_id: Int){
        viewModelScope.launch {
            _characterDetailData.postValue(repository.getCharacterDetails(mal_id))
        }
    }

    fun getTopReviewsList(){
        viewModelScope.launch {
            _topreviews.postValue(repository.getTopReviewsList())
        }
    }

    fun getTopAnimeList(page: Int){
        viewModelScope.launch {
            _anime.postValue(repository.getTopAnimeList(page))
        }
    }

    fun getTopCharactersList(page: Int){
        viewModelScope.launch {
            _characters.postValue(repository.getTopCharactersList(page))
        }
    }

    fun getFavoriteAnime(){
        CoroutineScope(Dispatchers.Main).launch {
            _dbData.postValue(AppDatabase.getAllAnime())
        }
    }
    fun addFavoriteAnime(entity: EntityAnime) {
        CoroutineScope(Dispatchers.Main).launch {
            val search = animeDbData.getAnimeById(entity.mal_id)
            if (search == null) {
                val insert = AppDatabase.insertAnime(entity)
                _insertStatus.postValue(insert)
            }else {
                deleteFavoriteAnime(entity)
            }
        }
    }
    fun deleteFavoriteAnime(entity: EntityAnime) {
        CoroutineScope(Dispatchers.IO).launch {
            val search = AppDatabase.getAnimeById(entity.mal_id)
            if (search != null) {
                dao.deleteAnime(entity)
                _insertStatus.postValue(null)
            }
        }
    }
    fun searchFavoriteAnime(mal_id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val value = dao.getAnimeById(mal_id)
            _dbSearch.postValue(value)

        }
    }


    }






