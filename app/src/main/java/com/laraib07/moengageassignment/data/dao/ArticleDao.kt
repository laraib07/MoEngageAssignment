package com.laraib07.moengageassignment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.laraib07.moengageassignment.data.model.Article
import kotlinx.coroutines.flow.Flow
import java.net.URL

private const val TABLE = Article.TABLE_NAME

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Article>)

    @Query("SELECT * FROM $TABLE")
    fun getAllArticles(): Flow<List<Article>>

    @Query("SELECT * FROM $TABLE WHERE url = :url")
    fun getArticleByURL(url: String) : Flow<Article?>
}