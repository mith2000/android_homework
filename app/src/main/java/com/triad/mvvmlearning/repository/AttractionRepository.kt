package com.triad.mvvmlearning.repository

import com.triad.mvvmlearning.model.AttractionModelV
import com.triad.mvvmlearning.network.AttractionApi
import com.triad.mvvmlearning.network.Resource

class AttractionRepository(private val api: AttractionApi) : BaseRepository() {

    suspend fun getAllAttractions(lang: String, page: Int? = 1): Resource<ArrayList<AttractionModelV>> {
        return safeApiCall {
            val response = api.getAllAttractions(lang, page)
            val attractionList = ArrayList<AttractionModelV>()
            response.data.forEach { attractionRaw ->
                attractionList.add(attractionRaw.raw2Model())
            }
            attractionList
        }
    }
}