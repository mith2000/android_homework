package com.triad.mvvmlearning.responses

import com.triad.mvvmlearning.model.BaseModel

abstract class BaseRaw<BM : BaseModel> {
    /**
     * Convert raw data to model to separate the dependency on the model class
     **/
    abstract fun raw2Model(): BM?
}
