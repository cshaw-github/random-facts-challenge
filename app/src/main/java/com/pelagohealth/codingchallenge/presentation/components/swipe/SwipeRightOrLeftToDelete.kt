package com.pelagohealth.codingchallenge.presentation.components.swipe

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import com.pelagohealth.codingchallenge.presentation.components.dimens.Dimens
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.math.sign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeRightOrLeftToDelete(
	onDelete: () -> Unit,
	content: @Composable BoxScope.() -> Unit,
) {
	var offsetX by remember { mutableFloatStateOf(0f) }
	var showConfirmation by remember { mutableStateOf(false) }
	var isSwipingRight by remember { mutableStateOf(true) }
	val swipeThreshold = 150f
	val maxOffset = 300f

	Box(
		modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        if (offsetX.absoluteValue > swipeThreshold) {
                            showConfirmation = true
                        } else {
                            offsetX = 0f
                        }
                    }
                ) { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount
                    isSwipingRight = dragAmount > 0
                    if (offsetX.absoluteValue > maxOffset) {
                        offsetX = maxOffset * offsetX.sign
                    }
                }
            }
	) {
		if (showConfirmation) {
			var paddingValues = when (isSwipingRight) {
				true -> PaddingValues(start = Dimens.medium)
				false -> PaddingValues(end = Dimens.medium)
			}

			Box(
				modifier = Modifier
                    .padding(paddingValues)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .align(if (isSwipingRight) Alignment.CenterStart else Alignment.CenterEnd)
			) {
				IconButton(
					onClick = {
						onDelete()
						showConfirmation = false
					}
				) {
					Icon(
						Icons.Outlined.Delete,
						contentDescription = null,
					)
				}
			}
		}
		Box(
			modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(offsetX.roundToInt(), 0) }
		) {
			content()
		}
	}
}

