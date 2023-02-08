package com.msilimon.rickandmorty.data

import com.msilimon.rickandmorty.data.dto.ResponseDto
import kotlinx.coroutines.delay
import javax.inject.Inject

class AppRepository @Inject constructor(private val apoService: ApiService) {
    suspend fun getPage(page: Int): ResponseDto {
        delay(5000)
        return apoService.getPage(page)
    }
}