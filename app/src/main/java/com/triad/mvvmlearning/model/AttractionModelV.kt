package com.triad.mvvmlearning.model

import android.os.Build
import android.os.Parcelable
import android.text.Html
import kotlinx.android.parcel.Parcelize

const val ATTRACTION_MODEL_VIEW_KEY = "attraction_key"
const val ATTRACTION_URL_KEY = "url_key"

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

    /**
     * This function converts the introduction text from HTML format to plain text.
     *
     * It first replaces all occurrences of "\r\n" in the introduction with "<br>" to format the introduction correctly for HTML.
     * Then, it uses the Html.fromHtml function to convert the formatted introduction from HTML to plain text.
     * If the Android SDK version is N or higher, it uses the FROM_HTML_MODE_COMPACT flag for Html.fromHtml.
     *
     * @return The introduction text converted from HTML to plain text.
     */
    fun getIntroductionFromHtml(): String {
        val formattedIntroduction = introduction.replace("\r\n", "<br>")
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(formattedIntroduction, Html.FROM_HTML_MODE_COMPACT).toString()
        } else {
            Html.fromHtml(formattedIntroduction).toString()
        }
    }

    /// Get first image if have or return empty string
    fun getFirstImageLink() = if (images.isNotEmpty()) images.first().getImageLink() else ""

    /**
     * This function generates a string of category names.
     *
     * It checks if the category list is not empty.
     * If it's not empty, it joins the names of the categories into a single string, separated by commas.
     *
     * @return A string of category names separated by commas, or "--" if the category list is empty.
     */
    fun getCategoryListName(): String =
        if (category.isNotEmpty()) category.joinToString(separator = ", ") { it.name } else "--"


    /**
     * This function generates a string of target names.
     *
     * It checks if the target list is not empty.
     * If it's not empty, it joins the names of the targets into a single string, separated by commas.
     *
     * @return A string of target names separated by commas, or "--" if the target list is empty.
     */
    fun getTargetListName(): String =
        if (target.isNotEmpty()) target.joinToString(separator = ", ") { it.name } else "--"


    /**
     * This function generates a string of service names.
     *
     * It checks if the service list is not empty.
     * If it's not empty, it joins the names of the services into a single string, separated by commas.
     *
     * @return A string of service names separated by commas, or "--" if the service list is empty.
     */
    fun getServiceListName(): String =
        if (service.isNotEmpty()) service.joinToString(separator = ", ") { it.name } else "--"


    /**
     * This function generates a string of friendly names.
     *
     * It checks if the friendly list is not empty.
     * If it's not empty, it joins the names of the friendlies into a single string, separated by commas.
     *
     * @return A string of friendly names separated by commas, or "--" if the friendly list is empty.
     */
    fun getFriendlyListName(): String =
        if (friendly.isNotEmpty()) friendly.joinToString(separator = ", ") { it.name } else "--"
}

@Parcelize
data class CategoryModelV(
    val id: Int, val name: String,
) : BaseModel(), Parcelable

@Parcelize
data class TargetModelV(
    val id: Int, val name: String,
) : BaseModel(), Parcelable

@Parcelize
data class ServiceModelV(
    val id: Int, val name: String,
) : BaseModel(), Parcelable

@Parcelize
data class FriendlyModelV(
    val id: Int, val name: String,
) : BaseModel(), Parcelable

@Parcelize
data class ImageModelV(
    val src: String, val subject: String, val ext: String,
) : BaseModel(), Parcelable {
    fun getImageLink() = src
}
