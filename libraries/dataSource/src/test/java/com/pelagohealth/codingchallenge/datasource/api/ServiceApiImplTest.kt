package com.pelagohealth.codingchallenge.datasource.api

import com.pelagohealth.codingchallenge.datasource.entities.FactEntity
import com.pelagohealth.codingchallenge.datasource.mapper.toDataLayer
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds

class ServiceApiImplTest {
	private lateinit var serviceApi: ServiceApiImpl

	@RelaxedMockK
	private lateinit var restApi: FactsRestApi

	@RelaxedMockK
	private lateinit var factEntity: FactEntity

	@Before
	fun setUp() {
		MockKAnnotations.init(this)
		serviceApi = ServiceApiImpl(restApi)
	}

	@Test
	fun getFact() = runTest {
		coEvery { restApi.getFactRandom() } returns factEntity

		withTimeoutOrNull(10.seconds) {
			serviceApi.getFact().collect { data ->
				assertTrue { data.data != null }
				data.data?.let {
					assertTrue { factEntity.toDataLayer() == it }
				}

			}
		}
	}
}
