package main.app.newspaper.network.interfaces

import main.app.newspaper.network.ArticleList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val NEWS_API_KEY = "f8077590bb894544a941b3a9fbf811c4"
private const val NEWS_COUNTRY_CODE = "us"

interface RetrofitService {
    @GET("top-headlines")
    fun getArticles(
        @Query("apiKey") key: String = NEWS_API_KEY,
        @Query("country") query: String = NEWS_COUNTRY_CODE
    ): Call<ArticleList>
}