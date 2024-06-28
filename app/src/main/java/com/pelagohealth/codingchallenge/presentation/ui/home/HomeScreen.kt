package com.pelagohealth.codingchallenge.presentation.ui.home

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.pelagohealth.codingchallenge.presentation.components.dimens.Dimens
import com.pelagohealth.codingchallenge.presentation.components.snackbar.PalegoSnackbar
import com.pelagohealth.codingchallenge.presentation.model.FactEntity
import com.pelagohealth.codingchallenge.presentation.model.UiStatus
import com.pelagohealth.codingchallenge.presentation.ui.ValidateUiStatus
import com.pelagohealth.codingchallenge.ui.theme.PelagoCodingChallengeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt


data object HomeScreen : Screen {
	private fun readResolve(): Any = HomeScreen

	@Composable
	override fun Content() {
		val coroutineScope = rememberCoroutineScope()
		val viewModel = hiltViewModel<HomeViewModel>()
		val snackbar = remember { PalegoSnackbar(SnackbarHostState()) }
		val newFact by viewModel.newFactState.collectAsState()
		val uiState by viewModel.uiState.collectAsState()
		val factList by viewModel.factListState.collectAsState()

		HomeContent(
			viewModel = viewModel,
			newFact = newFact,
			factList = factList,
			uiStatus = uiState,
			snackbar = snackbar,
			coroutineScope = coroutineScope,
		)
	}
}

@Composable
fun HomeContent(
	viewModel: HomeViewModel,
	newFact: FactEntity,
	factList: List<FactEntity>,
	snackbar: PalegoSnackbar,
	uiStatus: UiStatus,
	coroutineScope: CoroutineScope,
) {
	Scaffold(
		snackbarHost = {
			SnackbarHost(hostState = snackbar.snackbarHostState)
		},
	) { contentPadding ->

		Home(
			newFact = newFact,
			factList = factList,
			viewModel = viewModel,
			coroutineScope = coroutineScope,
			contentPadding = contentPadding
		)

		ValidateUiStatus(
			uiStatus = uiStatus,
			snackbar = snackbar,
			onActionRetry = {
				coroutineScope.launch(Dispatchers.IO) {
					viewModel.fetchNewFact()
				}
			},
		)
	}
}

@Composable
fun Home(
	modifier: Modifier = Modifier,
	newFact: FactEntity,
	factList: List<FactEntity>,
	viewModel: HomeViewModel,
	coroutineScope: CoroutineScope,
	contentPadding: PaddingValues,
) {
	Column(
		modifier = modifier
            .fillMaxSize()
            .padding(contentPadding),
		verticalArrangement = Arrangement.Top,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		Spacer(modifier = Modifier.height(Dimens.xLarge))
		Text(
			text = "${newFact.text}",
			modifier = Modifier.padding(start = Dimens.medium, end = Dimens.medium)
		)
		Spacer(modifier = Modifier.height(Dimens.medium))
		Button(onClick = {
			coroutineScope.launch(Dispatchers.IO) {
				viewModel.fetchNewFact()
			}
		}) {
			Text("More facts!")
		}
		Spacer(modifier = Modifier.height(Dimens.large))
		if (factList.isNotEmpty()) {
			FactList(facts = factList, contentPadding = contentPadding) {
				coroutineScope.launch(Dispatchers.IO) {
					viewModel.removeFact(it)
				}
			}
			Spacer(modifier = Modifier.height(Dimens.large))
		}
	}
}

@Composable
fun FactList(
	facts: List<FactEntity>,
	contentPadding: PaddingValues,
	onRemoveItem: (FactEntity) -> Unit
) {
	LazyColumn(
		modifier = Modifier
            .fillMaxWidth()
            .padding(contentPadding),
		verticalArrangement = Arrangement.spacedBy(Dimens.small)
	) {
		items(count = facts.size, key = { facts[it].timestamp }) { index ->
			var dismissed by remember { mutableStateOf(false) }
			if (!dismissed) {
				val item = facts[index]
				SwipeToDismissItem(
					item = item,
					onDismiss = {
						dismissed = true
						onRemoveItem(item)
					}
				)
			}
		}
	}
}

@Composable
fun FactItem(fact: FactEntity) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
	) {
		Text(
			modifier = Modifier.padding(
				start = Dimens.medium,
				top = Dimens.small,
				end = Dimens.medium
			),
			text = fact.text ?: "No fact text available"
		)
		Text(
			modifier = Modifier.padding(
				start = Dimens.medium,
				top = Dimens.small,
				bottom = Dimens.small,
				end = Dimens.medium
			),
			text = "Source: ${fact.sourceUrl}",
			color = LocalContentColor.current.copy(alpha = 0.5f)
		)
	}
}

@Composable
fun SwipeToDismissItem(
	item: FactEntity,
	onDismiss: () -> Unit
) {
	var offsetX by remember { mutableFloatStateOf(0f) }
	Box(
		modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.small)
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        if (offsetX.absoluteValue > 300) {
                            onDismiss()
                        }
                        offsetX = 0f
                    }
                ) { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount
                }
            }
            .offset { IntOffset(offsetX.roundToInt(), 0) }
	) {
		FactItem(fact = item)
	}
}


@Preview(showBackground = true)
@Composable
fun FactListPreview() {
	PelagoCodingChallengeTheme {
		Surface {
			FactList(facts, PaddingValues(Dimens.medium)) {}
		}
	}
}

val facts = listOf(
	FactEntity(
		timestamp = System.currentTimeMillis(),
		text = "The population of Earth is about 8 billion people.",
		sourceUrl = "https://kotlinlang.org/"
	),
	FactEntity(
		timestamp = System.currentTimeMillis(),
		text = "Kotlin is a statically typed programming language.",
		sourceUrl = "https://kotlinlang.org/"
	),
	FactEntity(
		timestamp = System.currentTimeMillis(),
		text = "Kotlin is my favourite language",
		sourceUrl = "https://kotlinlang.org/"
	)
)

