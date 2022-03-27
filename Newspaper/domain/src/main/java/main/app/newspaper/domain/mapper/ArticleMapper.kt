package main.app.newspaper.domain.mapper

import main.app.newspaper.domain.models.ArticleEntity
import main.app.newspaper.domain.network.Article

class ArticleMapper : Mapper<List<Article>, List<ArticleEntity>> {
    override fun map(input: List<Article>) = input.map { mapSingle(it) }

    private fun mapSingle(input: Article) = ArticleEntity(
        title = input.title,
        description = input.description,
        urlToImage = input.urlToImage,
        url = input.url
    )
}
