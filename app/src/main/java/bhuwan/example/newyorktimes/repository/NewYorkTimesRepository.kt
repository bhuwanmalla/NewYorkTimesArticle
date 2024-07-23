package bhuwan.example.newyorktimes.repository

import bhuwan.TopStoriesModel
import bhuwan.example.newyorktimes.constants.RetrofitBuilder
import bhuwan.example.newyorktimes.models.ArticlesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewYorkTimesRepository {

    //    Instance of retrofit builder
    private val apiService = RetrofitBuilder.api

    suspend fun fetchArticles(): ArticlesModel {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getArticles()
                response
            } catch (e: Exception) {
                ArticlesModel()
            }
        }
    }

    suspend fun getTopStories(): TopStoriesModel {
         return withContext(Dispatchers.IO) {
             try {
                 val response = apiService.fetchTopStories()
                 response
             } catch (e: Exception){
                 TopStoriesModel()
             }
        }
    }
}