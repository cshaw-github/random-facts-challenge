package com.pelagohealth.codingchallenge.datalayer.mapper

import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue
import com.pelagohealth.codingchallenge.datalayer.entities.FactEntity as DataLayerFactEntity

class MapperKtTest {
	private lateinit var dataLayerFact: DataLayerFactEntity

	@Before
	fun setUp() {
		dataLayerFact = DataLayerFactEntity(
			text = "text",
			sourceUrl = "sourceUrl",
		)
	}

	@Test
	fun toDataLayer() {
		val domainLayerFact = dataLayerFact.toDomainLayer()
		assertTrue { dataLayerFact.text == domainLayerFact.text }
		assertTrue { dataLayerFact.sourceUrl == domainLayerFact.sourceUrl }
	}
}
