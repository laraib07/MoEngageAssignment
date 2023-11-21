package com.laraib07.moengageassignment.di

import android.content.Context
import androidx.room.Room
import com.laraib07.moengageassignment.data.Database
import com.laraib07.moengageassignment.data.dao.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideArticleDao(database: Database): ArticleDao {
        return database.articleDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): Database {
        return Room.databaseBuilder(
            appContext,
            Database::class.java,
            Database.DATABASE_NAME
        ).build()
    }
}