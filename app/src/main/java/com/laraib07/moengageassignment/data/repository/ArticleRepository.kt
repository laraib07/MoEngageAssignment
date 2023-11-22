package com.laraib07.moengageassignment.data.repository

import android.util.Log
import com.google.gson.Gson
import com.laraib07.moengageassignment.data.dao.ArticleDao
import com.laraib07.moengageassignment.data.model.Article
import com.laraib07.moengageassignment.data.model.NetworkResponse
import com.laraib07.moengageassignment.data.model.asDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val articleDao: ArticleDao
) {
    val getAllArticles: Flow<List<Article>> = articleDao.getAllArticles()

    suspend fun fetchArticles() {
        CoroutineScope(Dispatchers.IO).launch {
            var httpURLConnection: HttpURLConnection? = null
            try {

                val url = URL("https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json")

                httpURLConnection = url.openConnection() as HttpURLConnection

                val code = httpURLConnection.responseCode

                if (code != 200) {
                    throw IOException("The error from the server is $code")
                }

                val bufferedReader = BufferedReader(
                    InputStreamReader(httpURLConnection.inputStream)
                )

                val jsonStringHolder: StringBuilder = StringBuilder()

                while (true) {
                    val readLine = bufferedReader.readLine() ?: break
                    jsonStringHolder.append(readLine)
                }


                val networkResponse =
                    Gson().fromJson(jsonStringHolder.toString(), NetworkResponse::class.java)

                Log.d("Repository", "${networkResponse.asDomainModel()}")

                articleDao.insertAll(networkResponse.asDomainModel())
            } catch (ioexception : IOException){
                Log.e(this.javaClass.name, ioexception.message.toString())
            } finally {
                httpURLConnection?.disconnect()
            }
        }
    }

    fun getArticleByURL(url: String): Flow<Article?> = articleDao.getArticleByURL(url)
}