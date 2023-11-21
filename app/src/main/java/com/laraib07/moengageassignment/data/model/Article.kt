package com.laraib07.moengageassignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Article.TABLE_NAME)
data class Article(
    val sourceId: String?,
    val sourceName: String?,
    val author: String?,
    val title: String?,
    val description: String?,
    val urlToImg: String?,
    val publishedAt: String?,
    val content: String?,
    @PrimaryKey val url: String
) {
    companion object {
        const val TABLE_NAME = "articles"
    }
}