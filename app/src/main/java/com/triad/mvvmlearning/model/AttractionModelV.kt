package com.triad.mvvmlearning.model

import android.os.Build
import android.os.Parcelable
import android.text.Html
import android.text.Spanned
import kotlinx.android.parcel.Parcelize

const val ATTRACTION_MODEL_VIEW_KEY = "attraction"

@Parcelize
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
    var friendly: List<FriendlyModelV> = arrayListOf(),
    var images: List<ImageModelV> = arrayListOf(),
) : BaseModel(), Parcelable {

    /// Formatting HTML to Spanned
    fun getIntroduction(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(introduction, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(introduction)
    }

    /// Manual formatting HTML to keep value as String to handle \n
    fun getIntroductionReplaced(): String = introduction
        .replace("&nbsp;", " ")
        .replace("&ldquo;", "\"")
        .replace("&rdquo;", "\"")
        .replace("&amp;", "&")
        .replace("&lt;", "<")
        .replace("&gt;", ">")
        .replace("&quot;", "\"")
        .replace("&apos;", "'")
        .replace("\\r\\n", "\n")
        .replace("\\t", "\t")

    /// Get first image if have for the Card layout
    fun getFirstImageLink() = if (images.isNotEmpty()) images.first().getImageLink() else ""

    /// Get list name of category, target, service and friendly
    fun getCategoryListName(): String =
        if (category.isNotEmpty()) category.joinToString(separator = ", ") { it.name } else "--"

    fun getTargetListName(): String =
        if (target.isNotEmpty()) target.joinToString(separator = ", ") { it.name } else "--"

    fun getServiceListName(): String =
        if (service.isNotEmpty()) service.joinToString(separator = ", ") { it.name } else "--"

    fun getFriendlyListName(): String =
        if (friendly.isNotEmpty()) friendly.joinToString(separator = ", ") { it.name } else "--"
}

@Parcelize
data class CategoryModelV(
    val id: Int, val name: String
) : BaseModel(), Parcelable

@Parcelize
data class TargetModelV(
    val id: Int, val name: String
) : BaseModel(), Parcelable

@Parcelize
data class ServiceModelV(
    val id: Int, val name: String
) : BaseModel(), Parcelable

@Parcelize
data class FriendlyModelV(
    val id: Int, val name: String
) : BaseModel(), Parcelable

@Parcelize
data class ImageModelV(
    val src: String, val subject: String, val ext: String,
) : BaseModel(), Parcelable {
    fun getImageLink() = src
}
