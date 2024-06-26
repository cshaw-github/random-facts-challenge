package com.pelagohealth.codingchallenge.domain.entities

import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class FactEntityTest {
	private lateinit var factEntity: FactEntity

	@Before
	fun setUp() {
		factEntity = FactEntity(
			text = "text",
			sourceUrl = "sourceUrl",
		)
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
