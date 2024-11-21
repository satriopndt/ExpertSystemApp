package com.satriopndt.expertsystem.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.satriopndt.expertsystem.data.model.ResearchModel
import com.satriopndt.expertsystem.data.repository.SystemRepository
import com.satriopndt.expertsystem.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val repository: SystemRepository): ViewModel() {

//    private val _uiState:MutableStateFlow<UiState<List<ResearchModel>>> = MutableStateFlow(UiState.Loading)
//
//    val uiState: StateFlow<UiState<List<ResearchModel>>> get() = _uiState

    private  val _groupPosting = MutableStateFlow(
        repository.getPosting()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupPosting: StateFlow<Map<Char, List<ResearchModel>>> get() = _groupPosting

    private val _query = mutableStateOf("")
    val query: State<String>get() = _query

    fun search(newQuery: String){
        _query.value = newQuery
        _groupPosting.value = repository.searchRiset(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }


}