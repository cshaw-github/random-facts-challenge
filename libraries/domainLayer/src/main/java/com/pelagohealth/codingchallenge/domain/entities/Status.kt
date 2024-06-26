package com.pelagohealth.codingchallenge.domain.entities

data class Status(
	val data: FactEntity? = null,
	val successful: Boolean = data != null,
	val error: String? = null,
)
