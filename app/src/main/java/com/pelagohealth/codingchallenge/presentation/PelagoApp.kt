package com.pelagohealth.codingchallenge.presentation

import android.app.Application
import com.pelagohealth.codingchallenge.presentation.di.initKoin

//@HiltAndroidApp
class PelagoApp : Application() {
	override fun onCreate() {
		super.onCreate()
		initKoin()
	}
}
