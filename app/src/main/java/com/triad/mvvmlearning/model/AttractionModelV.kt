package com.triad.mvvmlearning.model

data class AttractionModelV(
    val id: Int,
    val name: String,
    val name_zh: String?,
    val open_status: Int,
    val introduction: String,
    val open_time: String,
    val zipcode: String,
    val distric: String,
    val address: String,
    val tel: String,
    val fax: String,
    val email: String,
    val months: String,
    val nlat: Double,
    val elong: Double,
    val official_site: String,
    val facebook: String,
    val ticket: String,
    val remind: String,
    val staytime: String,
    val modified: String,
    val url: String,
    var category: List<CategoryModelV> = arrayListOf(),
    var target: List<TargetModelV> = arrayListOf(),
    var service: List<ServiceModelV> = arrayListOf(),
    var friendly: List<Any> = arrayListOf(),
    var images: List<ImageModelV> = arrayListOf(),
    var files: List<Any> = arrayListOf(),
    var links: List<Any> = arrayListOf(),
) : BaseModel()

data class CategoryModelV(
    val id: Int,
    val name: String
) : BaseModel()

data class TargetModelV(
    val id: Int,
    val name: String
) : BaseModel()

data class ServiceModelV(
    val id: Int,
    val name: String
) : BaseModel()

data class ImageModelV(
    val src: String, val subject: String, val ext: String,
) : BaseModel()
