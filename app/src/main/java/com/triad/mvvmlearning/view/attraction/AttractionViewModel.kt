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

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private var _currentLanguageOption: LanguageOption = LanguageOption.EN
    private val _attractionsResponse: MutableLiveData<Resource<ArrayList<AttractionModelV>>> =
        MutableLiveData()

    val isLoading: LiveData<Boolean> get() = _isLoading
    val attractions: LiveData<Resource<ArrayList<AttractionModelV>>>
        get() = _attractionsResponse

    init {
        getAllAttractions(_currentLanguageOption.code)
    }

    private fun getAllAttractions(lang: String, page: Int? = 1) = viewModelScope.launch {
        _isLoading.value = true
        _attractionsResponse.value = repository.getAllAttractions(lang, page)
        _isLoading.value = false
    }

    fun changeLanguageOption(option: LanguageOption) {
        if (_currentLanguageOption == option) return
        _currentLanguageOption = option
        getAllAttractions(option.code)
    }

    enum class LanguageOption(val code: String, val label: String) {
        ZH_TW("zh-tw", "正體中文"),
        ZH_CN("zh-cn", "簡體中文"),
        EN("en", "English"),
        JA("ja", "日本語"),
        KO("ko", "한국어"),
        ES("es", "Español"),
        ID("id", "Bahasa Indonesia"),
        TH("th", "ไทย"),
        VI("vi", "Tiếng Việt")
    }
}