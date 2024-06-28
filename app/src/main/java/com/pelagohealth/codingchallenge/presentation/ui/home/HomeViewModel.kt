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
	private val _newFactState = MutableStateFlow(FactEntity())
	val newFactState: StateFlow<FactEntity>
		get() = _newFactState

	private val _factListState = MutableStateFlow(emptyList<FactEntity>())
	val factListState: StateFlow<List<FactEntity>>
		get() = _factListState

	private val _uiState = MutableStateFlow<UiStatus>(UiStatus.None)
	val uiState: StateFlow<UiStatus>
		get() = _uiState

	init {
		viewModelScope.launch(IO) {
			fetchNewFact()
		}
	}

	suspend fun fetchNewFact() {
		useCase()
			.onStart { _uiState.value = UiStatus.Loading }
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
					if (_newFactState.value.timestamp != 0L) {
						processFactList()
					}
					_newFactState.value = factTimestamped

				}
				_uiState.value = UiStatus.Success
			}

			false -> {
				_uiState.value = UiStatus.Error(status.error ?: UNKNOWN_ERROR)
			}
		}
	}

	private fun processFactList() {
		val currentFacts = _factListState.value.toMutableList()
		val prevFact = _newFactState.value
		val newFacts = if (currentFacts.size <= 2) {
			currentFacts.add(prevFact)
			currentFacts
		} else {
			val sortedList = currentFacts.sortedBy { it.timestamp }
			val oldestItem = sortedList.first()
			sortedList.filter { it.timestamp != oldestItem.timestamp } + prevFact
		}
		_factListState.value = newFacts
	}

	fun removeFact(fact: FactEntity) {
		val newList = _factListState.value.toMutableList().filter { fact != it }
		_factListState.value = newList
	}
}
