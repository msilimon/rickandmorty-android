package com.msilimon.rickandmorty.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "species") val species: String,
    @Json(name = "image") val image: String,
    @Json(name = "status") val status: String,
    @Json(name = "location") val location: LocationDto
)