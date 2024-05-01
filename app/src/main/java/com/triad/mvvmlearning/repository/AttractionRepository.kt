package com.triad.mvvmlearning.repository

import com.triad.mvvmlearning.model.AttractionModelV
import com.triad.mvvmlearning.network.AttractionApi
import com.triad.mvvmlearning.network.Resource

class AttractionRepository(private val api: AttractionApi) : BaseRepository() {

    /**
     * This is a suspend function that fetches all attractions from the API.
     *
     * @param lang The language in which the data should be fetched. It's a String parameter.
     * @param page The page number for the data to be fetched. It's an optional parameter with a default value of 1.
     *
     * @return A Resource object containing an ArrayList of AttractionModelV. Each AttractionModelV represents an attraction.
     * The function makes a safe API call to fetch all attractions, converts the raw data to AttractionModelV, and adds them to the list.
     * If the API call is successful, it returns the list of attractions. In case of any error, it returns the error resource.
     *
     * Usage:
     * ```
     * val attractions = getAllAttractions("en", 2)
     * ```
     */
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