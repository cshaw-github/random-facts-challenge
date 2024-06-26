package com.pelagohealth.codingchallenge.presentation.di

import com.pelagohealth.codingchallenge.datalayer.di.dataLayerModule
import com.pelagohealth.codingchallenge.datasource.di.dataSourceModule
import com.pelagohealth.codingchallenge.domain.di.domainLayerModule
import com.pelagohealth.codingchallenge.presentation.ui.home.HomeViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val applicationLayerModule = module {
	factoryOf(::HomeViewModel)
}

fun initKoin() {
	startKoin {
		modules(
			dataSourceModule,
			dataLayerModule,
			domainLayerModule,
			applicationLayerModule,
		)
	}
}





