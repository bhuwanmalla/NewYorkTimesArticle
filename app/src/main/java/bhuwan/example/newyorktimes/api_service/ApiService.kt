package bhuwan.example.newyorktimes.api_service

import bhuwan.TopStoriesModel
import bhuwan.example.newyorktimes.constants.UrlConst
import bhuwan.example.newyorktimes.models.ArticlesModel
import retrofit2.http.GET

interface ApiService {

    @GET(UrlConst.ARTICLES_URL)
    suspend fun getArticles(): ArticlesModel

    @GET(UrlConst.TOP_STORIES_URL)
    suspend fun fetchTopStories(): TopStoriesModel

}