package com.pelagohealth.codingchallenge.presentation.mapper


import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue
import com.pelagohealth.codingchallenge.domain.entities.FactEntity as DomainLayerFactEntity

class MapperKtTest {
	private lateinit var domainLayerFact: DomainLayerFactEntity

	@Before
	fun setUp() {
		domainLayerFact = DomainLayerFactEntity(
			text = "text",
			sourceUrl = "sourceUrl",
		)
	}

	@Test
	fun toUI() {
		val uiFact = domainLayerFact.toUi()
		assertTrue { domainLayerFact.text == uiFact.text }
		assertTrue { domainLayerFact.sourceUrl == uiFact.sourceUrl }
		assertTrue { uiFact.timestamp == 0L }
	}
}
