package com.triad.mvvmlearning.view.attraction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triad.mvvmlearning.model.AttractionModelV
import com.triad.mvvmlearning.network.Resource
import com.triad.mvvmlearning.repository.AttractionRepository
import kotlinx.coroutines.launch

class AttractionViewModel(private var repository: AttractionRepository) : ViewModel() {

    private val _attractionsResponse: MutableLiveData<Resource<ArrayList<AttractionModelV>>> =
        MutableLiveData()

    val attractions: LiveData<Resource<ArrayList<AttractionModelV>>>
        get() = _attractionsResponse

    init {
        getAllAttractions("en")
    }

    private fun getAllAttractions(lang: String, page: Int? = 1) = viewModelScope.launch {
        _attractionsResponse.value = repository.getAllAttractions(lang, page)
    }

}