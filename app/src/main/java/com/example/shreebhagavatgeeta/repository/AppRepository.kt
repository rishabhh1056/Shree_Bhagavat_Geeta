package com.example.shreebhagavatgeeta.repository

import android.net.DnsResolver
import android.util.Log
import com.example.shreebhagavatgeeta.datasource.api.ApiUtilities
import com.example.shreebhagavatgeeta.models.chaptersItem
import com.example.shreebhagavatgeeta.models.versesItem
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class AppRepository {

    fun getAllChapters() : Flow<List<chaptersItem>> = callbackFlow {

        val callback = object : retrofit2.Callback<List<chaptersItem>>{
            override fun onResponse(
                call: Call<List<chaptersItem>>,
                response: Response<List<chaptersItem>>
            ) {
                if (response.isSuccessful && response.body() != null)
                {
                    trySend(response.body()!!)
                    close()
                }
                else
                {
                    Log.e("TAG", "onResponse: internet problem hai  ", )
                }

            }

            override fun onFailure(call: Call<List<chaptersItem>>, t: Throwable) {
                close(t)
            }
        }

        ApiUtilities.api.getAllChapter().enqueue(callback)

        awaitClose{}
    }

    fun getVerses(chapterNumber: Int) : Flow<List<versesItem>> = callbackFlow {

        val callback  = object : retrofit2.Callback<List<versesItem>>{
            override fun onResponse(
                call: Call<List<versesItem>>,
                response: Response<List<versesItem>>
            ) {
                if (response.isSuccessful && response.body() != null)
                {
                    trySend(response.body()!!)
                    close()
                }
            }

            override fun onFailure(call: Call<List<versesItem>>, t: Throwable) {
                close(t)
            }

        }
        ApiUtilities.api.getVerses(chapterNumber).enqueue(callback)
        awaitClose {}
    }

}