package com.pelagohealth.codingchallenge.presentation.model

import com.pelagohealth.codingchallenge.R
import com.pelagohealth.codingchallenge.utils.Uitls.NO_NETWORK
import com.pelagohealth.codingchallenge.utils.Uitls.UNKNOWN_ERROR

sealed class ErrorType(val errorType: String, val description: Int) {

	data object NetworkOffline : ErrorType(
		errorType = NO_NETWORK,
		description = R.string.no_internet,
	)

	data object UnknownError : ErrorType(
		errorType = UNKNOWN_ERROR,
		description = R.string.unknown_error,
	)
}
