package com.laraib07.moengageassignment.data.model

data class NetworkResponse(
    val articles: List<ArticleItem>
)

data class ArticleItem(
    val source: ArticleSource,
    val author: String,
    val title: String?,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
)

data class ArticleSource(
    val id: String,
    val name: String
)

/**
 * Convert network models to domain models
 */
fun NetworkResponse.asDomainModel(): List<Article> {
    return articles.map {
        Article(
            sourceId = it.source.id,
            sourceName = it.source.name,
            author = it.author,
            title = it.title,
            description = it.description,
            url = it.url,
            urlToImg = it.urlToImage,
            publishedAt = it.publishedAt,
            content = it.content
        )
    }
}