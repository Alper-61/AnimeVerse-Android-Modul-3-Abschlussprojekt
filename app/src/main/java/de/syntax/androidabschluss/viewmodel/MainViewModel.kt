package de.syntax.androidabschluss.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import de.syntax.androidabschluss.data.models.AnimeDetailModel
import de.syntax.androidabschluss.data.models.AnimeModel
import de.syntax.androidabschluss.CharacterDetailModel
import de.syntax.androidabschluss.data.models.CharactersModel
import de.syntax.androidabschluss.data.models.HomeTopReviewsModel
import de.syntax.androidabschluss.data.Repository
import de.syntax.androidabschluss.data.remote.AnimeApi
import de.syntax.androidabschluss.data.local.AppDatabase
import de.syntax.androidabschluss.data.local.AppDatabaseCharacters
import de.syntax.androidabschluss.data.local.EntityAnime
import de.syntax.androidabschluss.data.local.EntityCharacters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(
        AnimeApi.retrofitService,
        AppDatabase.getInstance(application),
        AppDatabaseCharacters.getInstance(application),


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
    val animeInsertStatus : LiveData<Long?> get() = _animeInsertStatus

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

    init {
        getFavoriteAnime()
        getFavoriteCharacters()
    }






    fun getAnimeList(page: Int) {
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



    fun getFavoriteAnime() {
        viewModelScope.launch(Dispatchers.IO) {
            _animeDbData.postValue(repository.getFavoriteAnime())
        }
    }

    fun addFavoriteAnime(entity: EntityAnime) {
        viewModelScope.launch(Dispatchers.IO) {
            val insertResult = repository.addFavoriteAnime(entity)
            _animeInsertStatus.postValue(insertResult)

        }
    }

    fun deleteFavoriteAnime(entity: EntityAnime) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavoriteAnime(entity)
            _animeInsertStatus.postValue(null)
        }
    }

    fun searchFavoriteAnime(mal_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchFavoriteAnime(mal_id)
            getFavoriteAnime()
        }
    }

    fun getFavoriteCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val favoritesCharacters = repository.getFavoriteCharacters()
            _dbData.postValue(favoritesCharacters)
        }
    }

    fun addFavoriteCharacter(entity: EntityCharacters) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.addFavoriteCharacter(entity)
            _insertStatus.postValue(result)
        }
    }

    fun deleteFavoriteCharacter(entity: EntityCharacters) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavoriteCharacter(entity)
            _insertStatus.postValue(null)
        }
    }

    fun searchFavoriteCharacter(mal_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val character = repository.searchFavoriteCharacter(mal_id)
            _dbSearch.postValue(character)
        }
    }



    private val _data = MutableLiveData<AnimeDetailModel>()
    val data : LiveData<AnimeDetailModel> get() = _data




    }










