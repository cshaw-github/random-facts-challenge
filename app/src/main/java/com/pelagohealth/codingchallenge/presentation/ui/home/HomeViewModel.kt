package com.pelagohealth.codingchallenge.presentation.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pelagohealth.codingchallenge.domain.entities.Status
import com.pelagohealth.codingchallenge.domain.usecase.GetFactUseCase
import com.pelagohealth.codingchallenge.presentation.mapper.toUi
import com.pelagohealth.codingchallenge.presentation.model.FactEntity
import com.pelagohealth.codingchallenge.presentation.model.UiStatus
import com.pelagohealth.codingchallenge.utils.Uitls.UNKNOWN_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val useCase: GetFactUseCase
) : ViewModel() {
	val newFactState: StateFlow<FactEntity>
		field = MutableStateFlow(FactEntity())

	val factListState: StateFlow<List<FactEntity>>
		field = MutableStateFlow(emptyList<FactEntity>())

	val uiState: StateFlow<UiStatus>
		field = MutableStateFlow<UiStatus>(UiStatus.None)

	init {
		viewModelScope.launch(IO) {
			fetchNewFact()
		}
	}

	suspend fun fetchNewFact() {
		useCase()
			.onStart { uiState.value = UiStatus.Loading }
			.onEach(::validateData)
			.catch {}
			.collect()
	}

	private fun validateData(
		status: Status,
	) {
		when (status.successful) {
			true -> {
				status.data?.let { newFact ->
					val factTimestamped =
						newFact.toUi().copy(timestamp = System.currentTimeMillis())
					if (newFactState.value.timestamp != 0L) {
						processFactList()
					}
					newFactState.value = factTimestamped

				}
				uiState.value = UiStatus.Success
			}

			false -> {
				uiState.value = UiStatus.Error(status.error ?: UNKNOWN_ERROR)
			}
		}
	}

	private fun processFactList() {
		val currentFacts = factListState.value.toMutableList()
		val prevFact = newFactState.value
		val newFacts = if (currentFacts.size <= 2) {
			currentFacts.add(prevFact)
			currentFacts
		} else {
			val sortedList = currentFacts.sortedBy { it.timestamp }
			val oldestItem = sortedList.first()
			sortedList.filter { it.timestamp != oldestItem.timestamp } + prevFact
		}
		factListState.value = newFacts
	}

	fun removeFact(fact: FactEntity) {
		val newList = factListState.value.toMutableList().filter { fact != it }
		factListState.value = newList
	}
}
