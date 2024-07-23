package bhuwan.example.newyorktimes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bhuwan.TopStoriesModel
import bhuwan.example.newyorktimes.repository.NewYorkTimesRepository
import kotlinx.coroutines.launch

class TopStoriesViewModel : ViewModel() {

    val repository = NewYorkTimesRepository()

    private val _topStories = MutableLiveData<TopStoriesModel>()
    val topStories: LiveData<TopStoriesModel> = _topStories

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getStories()
    }

    private fun getStories() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val stories = repository.getTopStories()
                _topStories.value = stories
            } catch (e: Exception) {
                _topStories.value = TopStoriesModel()
            } finally {
                _isLoading.value = false
            }
        }
    }


}