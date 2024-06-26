package com.pelagohealth.codingchallenge.presentation.components.snackbar

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

typealias OnDismiss = () -> Unit
typealias OnActionPerformed = () -> Unit

class PalegoSnackbar(val snackbarHostState: SnackbarHostState) {

    @Composable
    fun showMessage(
		message: String,
		actionLabel: String? = null,
		onDismiss: OnDismiss = {},
		onActionPerformed: OnActionPerformed = {},
    ) {
        LaunchedEffect(snackbarHostState) {
            val result =
                snackbarHostState.showSnackbar(message = message, actionLabel = actionLabel)
            when (result) {
                SnackbarResult.Dismissed -> {
                    onDismiss()
                }

                SnackbarResult.ActionPerformed -> {
                    onActionPerformed()
                }
            }
        }
    }
}
