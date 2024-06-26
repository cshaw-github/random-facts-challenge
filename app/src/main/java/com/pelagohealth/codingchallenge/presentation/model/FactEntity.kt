package com.pelagohealth.codingchallenge.presentation.model

data class FactEntity(
	val timestamp: Long = 0,
	val text: String? = null,
	val sourceUrl: String? = null
)
