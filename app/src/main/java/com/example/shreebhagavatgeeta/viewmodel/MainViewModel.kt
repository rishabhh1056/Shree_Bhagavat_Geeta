package com.example.shreebhagavatgeeta.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shreebhagavatgeeta.models.chaptersItem
import com.example.shreebhagavatgeeta.models.versesItem
import com.example.shreebhagavatgeeta.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel :ViewModel() {

    val appRepository = AppRepository()

    fun getAllChapters() : Flow<List<chaptersItem>> = appRepository.getAllChapters()

    fun getVerses(chapterNumber: Int) : Flow<List<versesItem>> = appRepository.getVerses(chapterNumber)


}