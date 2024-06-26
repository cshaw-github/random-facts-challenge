package com.pelagohealth.codingchallenge.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.pelagohealth.codingchallenge.R
import com.pelagohealth.codingchallenge.presentation.components.progressbar.CircularProgress
import com.pelagohealth.codingchallenge.presentation.components.snackbar.OnActionPerformed
import com.pelagohealth.codingchallenge.presentation.components.snackbar.PalegoSnackbar
import com.pelagohealth.codingchallenge.presentation.model.ErrorType
import com.pelagohealth.codingchallenge.utils.Uitls
import com.pelagohealth.codingchallenge.presentation.model.UiStatus

@Composable
fun ValidateUiStatus(
	uiStatus: UiStatus,
	snackbar: PalegoSnackbar,
	progressBarMessage: String = stringResource(R.string.please_wait),
	onActionRetry: OnActionPerformed = {},
) {
	when (uiStatus) {
		is UiStatus.Error -> snackbar.showMessage(
			message = stringResource(getErrorMessage(uiStatus.message)),
			actionLabel = stringResource(R.string.try_again),
			onActionPerformed = onActionRetry,
		)

		is UiStatus.Loading -> {
			CircularProgress(message = progressBarMessage)
		}

		is UiStatus.None,
		is UiStatus.Success -> {
			/*Do Nothing*/
		}
	}
}

private fun getErrorMessage(errorType: String): Int = when (errorType) {
	Uitls.NO_NETWORK -> ErrorType.NetworkOffline.description
	else -> ErrorType.UnknownError.description
}
