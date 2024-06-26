package com.pelagohealth.codingchallenge.domain.entities

import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class StatusTest {

	private lateinit var status: Status

	private val factEntity = FactEntity(
		text = "text",
		sourceUrl = "source",
	)

	@Before
	fun setUp() {
		status = Status(
			data = factEntity,
			successful = true,
			error = "Not Found"
		)
	}

	@Test
	fun getData() {
		assertTrue { status.data == factEntity }
	}

	@Test
	fun getSuccessful() {
		assertTrue { status.successful }
	}

	@Test
	fun getError() {
		assertTrue { status.error == "Not Found" }
	}
}
