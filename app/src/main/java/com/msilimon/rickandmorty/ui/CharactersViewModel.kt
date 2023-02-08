package com.msilimon.rickandmorty.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.msilimon.rickandmorty.data.AppRepository
import com.msilimon.rickandmorty.data.dto.CharacterDto
import com.msilimon.rickandmorty.data.PagingDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    val pagedCharacters: Flow<PagingData<CharacterDto>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { PagingDataSource(repository) }
    ).flow.cachedIn(viewModelScope)
}