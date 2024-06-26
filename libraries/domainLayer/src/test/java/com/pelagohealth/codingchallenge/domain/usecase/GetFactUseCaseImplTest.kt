package com.pelagohealth.codingchallenge.domain.usecase

import com.pelagohealth.codingchallenge.domain.entities.FactEntity
import com.pelagohealth.codingchallenge.domain.entities.Status
import com.pelagohealth.codingchallenge.domain.repositories.FactRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class GetFactUseCaseImplTest {

	@MockK
	private lateinit var repository: FactRepository

	@Before
	fun setUp() {
		MockKAnnotations.init(this, relaxUnitFun = true)
	}

	@Test
	fun getFactUseCaseSuccessfullyTest(): Unit = runTest {
		val response = getStatus(success = true)
		coEvery { repository.getFact() } returns flowOf(response)

		val useCase = GetFactUseCaseImpl(repository)

		useCase.invoke().collect { status ->
			assertTrue { status.successful }
			assertTrue { response.data == status.data }
		}
	}

	@Test
	fun getFactUseCaseReturnsErrorTest(): Unit = runTest {
		coEvery { repository.getFact() } returns flowOf(getStatus(success = false))

		val useCase = GetFactUseCaseImpl(repository)

		useCase.invoke().collect { status ->
			assertTrue { !status.successful }
			assertTrue { status.data == null }
			assertTrue { status.error != null }
			assertTrue { status.error == "Bad Request" }
		}
	}

	private fun getStatus(success: Boolean): Status {
		val data = FactEntity(
			text = "text",
			sourceUrl = "sourceUrl",
		)

		return Status(
			successful = success,
			data = if (success) data else null,
			error = if (success) null else "Bad Request"
		)
	}
}
