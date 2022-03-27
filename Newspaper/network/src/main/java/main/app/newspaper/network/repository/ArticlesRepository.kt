package main.app.newspaper.network.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import main.app.newspaper.domain.mapper.ArticleMapper
import main.app.newspaper.domain.models.ArticleEntity
import main.app.newspaper.network.ArticleList
import main.app.newspaper.network.common.Common
import main.app.newspaper.network.interfaces.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesRepository {
    private lateinit var mService: RetrofitService
    val articlesList = MutableLiveData<List<ArticleEntity>>()

    fun getAllArticlesList() {
        mService = Common.retrofitService
        val articleMapper = ArticleMapper()
        mService.getArticles().enqueue(object : Callback<ArticleList> {
            override fun onFailure(call: Call<ArticleList>, t: Throwable) {
                Log.e("IsFailResponse", t.message.toString())
            }

            override fun onResponse(call: Call<ArticleList>, response: Response<ArticleList>) {
                if (response.isSuccessful) {
                    articlesList.postValue(articleMapper.map(response.body()!!.articles!!.toList()))
                } else {
                    Log.e("Critical", response.toString())
                }
            }
        })
    }
}