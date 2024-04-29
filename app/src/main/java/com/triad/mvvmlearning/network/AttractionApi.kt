package com.triad.mvvmlearning.network

import com.triad.mvvmlearning.responses.attraction.AttractionResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface AttractionApi {

    @Headers("Accept: application/json")
    @GET("https://www.travel.taipei/open-api/{lang}/Attractions/All?page=1")
    suspend fun getAllAttractions(
        @Path("lang") lang: String = "en",
    ): AttractionResponse

}