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
import de.syntax.androidabschluss.data.local.EntityAnime
import de.syntax.androidabschluss.data.local.EntityCharacters
import kotlinx.coroutines.launch

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

    fun getAnimeDetails(malId: Int) {
        viewModelScope.launch {
            animeDetail.value = repository.getAnimeDetails(malId)
        }
    }

    // Weitere Funktionen zur Interaktion mit dem Repository
}
