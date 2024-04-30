package com.triad.mvvmlearning.view.attraction.detail

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triad.mvvmlearning.model.ATTRACTION_MODEL_VIEW_KEY
import com.triad.mvvmlearning.model.AttractionModelV
import com.triad.mvvmlearning.repository.AttractionDetailRepository

class AttractionDetailViewModel(private var repository: AttractionDetailRepository) : ViewModel() {

    private val _attraction: MutableLiveData<AttractionModelV> = MutableLiveData()

    val attraction: LiveData<AttractionModelV>
        get() = _attraction

    fun handleArgument(bundle: Bundle?) {
        _attraction.value = bundle?.getParcelable(ATTRACTION_MODEL_VIEW_KEY)
    }
}