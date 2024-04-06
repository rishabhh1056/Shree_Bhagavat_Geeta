package com.example.shreebhagavatgeeta.datasource.api

import com.example.shreebhagavatgeeta.models.chaptersItem
import com.example.shreebhagavatgeeta.models.versesItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiInterface {



    @GET("/v2/chapters/")
    fun getAllChapter() : Call<List<chaptersItem>>

    @GET("/v2/chapters/{ChapterNumber}/verses/")
    fun getVerses(@Path("ChapterNumber") chapterNumber: Int): Call<List<versesItem>>
}