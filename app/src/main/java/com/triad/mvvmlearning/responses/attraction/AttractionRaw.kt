package com.triad.mvvmlearning.responses.attraction

import com.triad.mvvmlearning.model.AttractionModelV
import com.triad.mvvmlearning.model.CategoryModelV
import com.triad.mvvmlearning.model.ImageModelV
import com.triad.mvvmlearning.model.ServiceModelV
import com.triad.mvvmlearning.model.TargetModelV
import com.triad.mvvmlearning.responses.BaseRaw

data class AttractionResponse(
    var data: List<AttractionRaw> = arrayListOf(),
    var total: Int? = 0,
)

data class AttractionRaw(
    val id: Int = -1,
    val name: String? = null,
    val name_zh: String? = null,
    val open_status: Int? = null,
    val introduction: String? = null,
    val open_time: String? = null,
    val zipcode: String? = null,
    val distric: String? = null,
    val address: String? = null,
    val tel: String? = null,
    val fax: String? = null,
    val email: String? = null,
    val months: String? = null,
    val nlat: Double? = null,
    val elong: Double? = null,
    val official_site: String? = null,
    val facebook: String? = null,
    val ticket: String? = null,
    val remind: String? = null,
    val staytime: String? = null,
    val modified: String? = null,
    val url: String? = null,
    val category: List<CategoryRaw>? = arrayListOf(),
    val target: List<TargetRaw>? = arrayListOf(),
    val service: List<ServiceRaw>? = arrayListOf(),
    val friendly: List<Any>? = arrayListOf(),
    val images: List<ImageRaw>? = arrayListOf(),
    val files: List<Any>? = arrayListOf(),
    val links: List<Any>? = arrayListOf(),
) : BaseRaw<AttractionModelV>() {
    override fun raw2Model(): AttractionModelV {
        val model = AttractionModelV(
            id = id,
            name = name ?: "--",
            name_zh = name_zh,
            open_status = open_status ?: -1,
            introduction = introduction ?: "--",
            open_time = open_time ?: "--",
            zipcode = zipcode ?: "--",
            distric = distric ?: "--",
            address = address ?: "--",
            tel = tel ?: "--",
            fax = fax ?: "--",
            email = email ?: "--",
            months = months ?: "--",
            nlat = nlat ?: 0.0,
            elong = elong ?: 0.0,
            official_site = official_site ?: "--",
            facebook = facebook ?: "--",
            ticket = ticket ?: "--",
            remind = remind ?: "--",
            staytime = staytime ?: "--",
            modified = modified ?: "--",
            url = url ?: "--",
        )

        val listCategory: ArrayList<CategoryModelV> = arrayListOf()
        category?.forEach {
            listCategory.add(it.raw2Model())
        }
        model.category = listCategory

        val listTarget: ArrayList<TargetModelV> = arrayListOf()
        target?.forEach {
            listTarget.add(it.raw2Model())
        }
        model.target = listTarget

        val listService: ArrayList<ServiceModelV> = arrayListOf()
        service?.forEach {
            listService.add(it.raw2Model())
        }
        model.service = listService

        val listImage: ArrayList<ImageModelV> = arrayListOf()
        images?.forEach {
            listImage.add(it.raw2Model())
        }
        model.images = listImage

        return model
    }
}

data class CategoryRaw(
    val id: Int = -1, val name: String? = null,
) : BaseRaw<CategoryModelV>() {
    override fun raw2Model(): CategoryModelV {
        return CategoryModelV(id = id, name = name ?: "--")
    }
}

data class TargetRaw(
    val id: Int = -1, val name: String? = null,
) : BaseRaw<TargetModelV>() {
    override fun raw2Model(): TargetModelV {
        return TargetModelV(id = id, name = name ?: "--")
    }
}

data class ServiceRaw(
    val id: Int = -1, val name: String? = null,
) : BaseRaw<ServiceModelV>() {
    override fun raw2Model(): ServiceModelV {
        return ServiceModelV(id = id, name = name ?: "--")
    }
}

data class ImageRaw(
    val src: String? = null, val subject: String? = null, val ext: String? = null,
) : BaseRaw<ImageModelV>() {
    override fun raw2Model(): ImageModelV {
        return ImageModelV(src = src ?: "", subject = subject ?: "", ext = ext ?: "")
    }
}
