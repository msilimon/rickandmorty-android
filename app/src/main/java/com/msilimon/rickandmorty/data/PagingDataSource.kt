package com.msilimon.rickandmorty.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.msilimon.rickandmorty.data.dto.CharacterDto

class PagingDataSource(private val repository: AppRepository) : PagingSource<Int, CharacterDto>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterDto>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            repository.getPage(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.results,
                    prevKey = null,
                    nextKey = if (it.results.isEmpty()) null else page + FIRST_PAGE
                )
            }, onFailure = { LoadResult.Error(it) }
        )
    }

    private companion object {
        private const val FIRST_PAGE = 1
    }
}