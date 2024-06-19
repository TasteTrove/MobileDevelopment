package com.example.tastetrove.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("UserResponse")
	val userResponse: UserResponseItem
)

data class UserResponseItem(

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String
)
