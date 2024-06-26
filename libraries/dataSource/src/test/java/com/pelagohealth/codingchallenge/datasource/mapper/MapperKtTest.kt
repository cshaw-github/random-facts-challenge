package com.pelagohealth.codingchallenge.datasource.mapper

import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue
import com.pelagohealth.codingchallenge.datasource.entities.FactEntity as DataSourceFactEntity


class MapperKtTest {
	private lateinit var dataSourceFact: DataSourceFactEntity

	@Before
	fun setUp() {
		dataSourceFact = DataSourceFactEntity(
			id = "1",
			text = "text",
			source = "source",
			sourceUrl = "sourceUrl",
			language = "language",
			permalink = "permalink"
		)
	}

	@Test
	fun toDataLayer() {
		val dataLayerFact = dataSourceFact.toDataLayer()
		assertTrue { dataLayerFact.text == dataSourceFact.text }
		assertTrue { dataLayerFact.sourceUrl == dataSourceFact.sourceUrl }
	}
}
