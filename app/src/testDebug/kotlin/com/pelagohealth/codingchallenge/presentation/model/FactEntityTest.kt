package com.pelagohealth.codingchallenge.presentation.model


import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class FactEntityTest {
	private lateinit var factEntity: FactEntity

	@Before
	fun setUp() {
		factEntity = FactEntity(
			timestamp = 2L,
			text = "text",
			sourceUrl = "sourceUrl",
		)
	}

	@Test
	fun getTimestamp() {
		assertTrue { factEntity.timestamp == 2L }
	}

	@Test
	fun getText() {
		assertTrue { factEntity.text == "text" }
	}

	@Test
	fun getSourceUrl() {
		assertTrue { factEntity.sourceUrl == "sourceUrl" }
	}
}
