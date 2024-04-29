package com.triad.mvvmlearning.responses

import com.triad.mvvmlearning.model.BaseModel

abstract class BaseRaw<BM : BaseModel> {
    abstract fun raw2Model(): BM?
}
