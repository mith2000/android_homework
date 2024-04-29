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

    private val _loginResponse: MutableLiveData<Resource<ArrayList<AttractionModelV>>> =
        MutableLiveData()

    val loginResponse: LiveData<Resource<ArrayList<AttractionModelV>>>
        get() = _loginResponse

    fun getAllAttractions(lang: String, page: Int? = 1) = viewModelScope.launch {
        _loginResponse.value = repository.getAllAttractions(lang, page)
    }

}