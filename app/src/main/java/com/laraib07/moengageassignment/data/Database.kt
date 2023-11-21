package com.laraib07.moengageassignment.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.laraib07.moengageassignment.data.dao.ArticleDao
import com.laraib07.moengageassignment.data.model.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        const val DATABASE_NAME = "news_db"
    }
}