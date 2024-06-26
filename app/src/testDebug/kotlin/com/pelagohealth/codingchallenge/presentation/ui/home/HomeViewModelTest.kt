package com.pelagohealth.codingchallenge.presentation.ui.home

import com.pelagohealth.codingchallenge.domain.entities.FactEntity
import com.pelagohealth.codingchallenge.domain.entities.Status
import com.pelagohealth.codingchallenge.domain.usecase.GetFactUseCase
import com.pelagohealth.codingchallenge.presentation.mapper.toUi
import com.pelagohealth.codingchallenge.presentation.model.UiStatus
import com.pelagohealth.codingchallenge.utils.Uitls.NO_NETWORK
import com.pelagohealth.codingchallenge.utils.Uitls.UNKNOWN_ERROR
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class HomeViewModelTest {
	@MockK
	private lateinit var getFactUseCase: GetFactUseCase

	@Before
	fun setUp() {
		MockKAnnotations.init(this, relaxUnitFun = true)
	}

	@Test
	fun testGetFactSuccessfully() = runTest {
		val fact = FactEntity(text = "text", sourceUrl = "sourceUrl")
		coEvery { getFactUseCase.invoke() } returns flowOf(Status(data = fact))

		val viewModel = HomeViewModel(getFactUseCase)

		viewModel.fetchNewFact()
		assertTrue { viewModel.newFactState.value.text == fact.toUi().text }
		assertTrue { viewModel.newFactState.value.sourceUrl == fact.toUi().sourceUrl }
		assertTrue { viewModel.newFactState.value.timestamp != 0L }
		assertTrue { viewModel.uiState.value == UiStatus.Success }
	}

	@Test
	fun testGetAllCakesNoInternetError() = runTest {
		coEvery { getFactUseCase.invoke() } returns flowOf(
			Status(error = NO_NETWORK)
		)

		val viewModel = HomeViewModel(getFactUseCase)

		viewModel.fetchNewFact()
		assertTrue {
			viewModel.uiState.value is UiStatus.Error &&
				(viewModel.uiState.value as UiStatus.Error).message == NO_NETWORK
		}
	}

	@Test
	fun testGetAllCakesUnknownError() = runTest {
		coEvery { getFactUseCase.invoke() } returns flowOf(
			Status(error = null)
		)

		val viewModel = HomeViewModel(getFactUseCase)

		viewModel.fetchNewFact()
		assertTrue {
			viewModel.uiState.value is UiStatus.Error &&
				(viewModel.uiState.value as UiStatus.Error).message == UNKNOWN_ERROR
		}
	}
}
