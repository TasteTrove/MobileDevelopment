package com.example.tastetrove.data.response

import com.google.gson.annotations.SerializedName

data class FoodsResponse(

	@field:SerializedName("FoodsResponse")
	val foodsResponse: List<FoodsResponseItem> = emptyList(),

	@field:SerializedName("error")
	val error: Boolean? = null,

	)

data class FoodGambarsItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("FoodId")
	val foodId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class FoodsResponseItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("Food_gambars")
	val foodGambars: List<FoodGambarsItem> = emptyList(),

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
