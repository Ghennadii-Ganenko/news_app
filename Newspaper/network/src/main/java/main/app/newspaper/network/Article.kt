package main.app.newspaper.network

import com.google.gson.annotations.SerializedName
import main.app.newspaper.domain.network.Article

class Article : Article {

    @SerializedName("author")
    override var author: String? = null

    @SerializedName("title")
    override val title: String? = null

    @SerializedName("description")
    override val description: String? = null

    @SerializedName("url")
    override lateinit var url: String

    @SerializedName("urlToImage")
    override val urlToImage: String? = null

    @SerializedName("publishedAt")
    override var publishedAt: String? = null

    @SerializedName("content")
    override var content: String? = null
}