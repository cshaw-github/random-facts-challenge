package com.pelagohealth.codingchallenge.datalayer.di

import com.pelagohealth.codingchallenge.datalayer.repository.FactRepositoryImpl
import com.pelagohealth.codingchallenge.domain.repositories.FactRepository
import org.koin.dsl.module

//import com.pelagohealth.codingchallenge.datalayer.repository.FactRepositoryImpl
//import com.pelagohealth.codingchallenge.domain.repositories.FactRepository
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ViewModelComponent
//
//@Module
//@InstallIn(ViewModelComponent::class)
//internal interface Module {
//	@Binds
//	fun bindFactRepository(repository: FactRepositoryImpl): FactRepository
//}

val dataLayerModule = module {
	single<FactRepository> { FactRepositoryImpl(get()) }
}
