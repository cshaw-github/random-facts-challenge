package com.pelagohealth.codingchallenge.datasource.entities

import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class FactEntityTest {

	private lateinit var factEntity: FactEntity

	@Before
	fun setUp() {
		factEntity = FactEntity(
			id = "1",
			text = "text",
			source = "source",
			sourceUrl = "sourceUrl",
			language = "language",
			permalink = "permalink"
		)
	}

	@Test
	fun getId() {
		assertTrue { factEntity.id == "1" }
	}

	@Test
	fun getText() {
		assertTrue { factEntity.text == "text" }
	}

	@Test
	fun getSource() {
		assertTrue { factEntity.source == "source" }
	}

	@Test
	fun getSourceUrl() {
		assertTrue { factEntity.sourceUrl == "sourceUrl" }
	}

	@Test
	fun getLanguage() {
		assertTrue { factEntity.language == "language" }
	}

	@Test
	fun getPermalink() {
		assertTrue { factEntity.permalink == "permalink" }
	}
}
