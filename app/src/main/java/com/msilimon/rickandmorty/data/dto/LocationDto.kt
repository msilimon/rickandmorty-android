package com.msilimon.rickandmorty.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationDto(@Json(name = "name") val name: String)