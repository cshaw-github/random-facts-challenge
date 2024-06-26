package com.pelagohealth.codingchallenge.presentation.model

sealed class UiStatus {
    data object None : UiStatus()
    data object Loading : UiStatus()
    data object Success : UiStatus()
    class Error(val message: String) : UiStatus()
}
