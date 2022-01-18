package com.aquiladvx.infinitywordle.ui.viewmodel

import androidx.lifecycle.*
import com.aquiladvx.infinitywordle.core.DEFAULT_ERROR
import com.aquiladvx.infinitywordle.core.GET_WORD_ERROR
import com.aquiladvx.infinitywordle.data.Resource
import com.aquiladvx.infinitywordle.data.entities.Filter
import com.aquiladvx.infinitywordle.data.entities.RandomWordRequest
import kotlinx.coroutines.launch
import com.aquiladvx.infinitywordle.data.repository.GameRepository
import kotlinx.coroutines.flow.collect


/*
 * Davi Aquila
 * aquiladvx
 *
 * 18/01/2022
 */
class GameViewModel(private val dataSource: GameRepository): ViewModel() {

    private val _getWordLiveData = MutableLiveData<Resource<String>>()
    val selectedWord: LiveData<Resource<String>> = _getWordLiveData

    fun getNewWord() {
        viewModelScope.launch {
            _getWordLiveData.value = Resource.Loading()
            dataSource.getNewWord(getNewWordRequestBody()).collect { resource ->
                if (resource.isSuccessful) {
                    val body = resource.body()
                    if (body != null) {
                       val selectedWord = body.getRandomWord()
                       _getWordLiveData.value = Resource.Success(selectedWord)
                    } else {
                        _getWordLiveData.value = Resource.DataError(GET_WORD_ERROR)
                    }
                } else {
                    val error = resource.errorBody()
                    //TODO: tratar erros de chamada
                    _getWordLiveData.value = Resource.DataError(DEFAULT_ERROR)
                }
            }

        }
    }

    private fun getNewWordRequestBody(): RandomWordRequest {
        val filter = Filter("", "", 5, 5, "")
        val request = RandomWordRequest("Palavras comuns (1000)", filter, "Português", 1)
        return request
    }

    class GameViewModelFactory(
        private val dataSource: GameRepository,
    ) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            GameViewModel(dataSource) as T
    }
}