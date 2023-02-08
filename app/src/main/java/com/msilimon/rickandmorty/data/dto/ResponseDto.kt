package com.msilimon.rickandmorty.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseDto(@Json(name = "results") val results: List<CharacterDto>)