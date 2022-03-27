package main.app.newspaper.domain.network

interface Article {
    val author: String?

    val title: String?

    val description: String?

    val url: String

    val urlToImage: String?

    val publishedAt: String?

    val content: String?
}