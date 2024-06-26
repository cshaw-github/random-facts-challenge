package com.pelagohealth.codingchallenge.datasource.entities

import com.google.gson.annotations.SerializedName

data class FactEntity(
	val id: String,
	val text: String,
	val source: String,
	@SerializedName("source_url")
	val sourceUrl: String,
	val language: String,
	val permalink: String,
)
