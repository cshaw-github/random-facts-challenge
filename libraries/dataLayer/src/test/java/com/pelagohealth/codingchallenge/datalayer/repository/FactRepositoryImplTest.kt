package com.pelagohealth.codingchallenge.datalayer.repository

import com.pelagohealth.codingchallenge.datalayer.datasource.ServiceApi
import com.pelagohealth.codingchallenge.datalayer.entities.FactEntity
import com.pelagohealth.codingchallenge.datalayer.entities.Response
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class FactRepositoryImplTest {
	@MockK
	private lateinit var serviceApi: ServiceApi

	@Before
	fun setUp() {
		MockKAnnotations.init(this, relaxUnitFun = true)
	}

	@Test
	fun getCakesSuccessfullyTest(): Unit = runTest {
		val factEntity = FactEntity(
			text = "text",
			sourceUrl = "sourceUrl",
		)
		val response = Response.success(factEntity)
		coEvery { serviceApi.getFact() } returns flowOf(response)

		FactRepositoryImpl(serviceApi).getFact().collect { status ->
			assertTrue { status.successful }
			assertTrue { status.data != null }
			assertTrue { response.data == factEntity }
		}
	}

	@Test
	fun getCakesReturnErrorTest(): Unit = runTest {
		val response = Response.error("Bad Request")
		coEvery { serviceApi.getFact() } returns flowOf(response)

		FactRepositoryImpl(serviceApi).getFact().collect { status ->
			assertTrue { !status.successful }
			assertTrue { status.data == null }
			assertTrue { status.error == "Bad Request" }
		}
	}
}
