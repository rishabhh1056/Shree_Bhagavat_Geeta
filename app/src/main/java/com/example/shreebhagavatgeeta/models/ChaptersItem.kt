package com.example.shreebhagavatgeeta.models

data class chaptersItem(
    val chapter_number: Int,
    val chapter_summary: String,
    val chapter_summary_hindi: String,
    val id: Int,
    val name: String,
    val name_meaning: String,
    val name_translated: String,
    val name_transliterated: String,
    val slug: String,
    val verses_count: Int
)