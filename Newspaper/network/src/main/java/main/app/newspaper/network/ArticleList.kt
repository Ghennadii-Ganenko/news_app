package main.app.newspaper.network

import com.google.gson.annotations.SerializedName

class ArticleList {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("totalResults")
    var totalResults: Int? = null

    @SerializedName("articles")
    var articles: List<Article>? = null
}