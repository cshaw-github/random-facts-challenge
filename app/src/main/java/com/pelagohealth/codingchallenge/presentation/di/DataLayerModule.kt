package com.pelagohealth.codingchallenge.presentation.di

import com.pelagohealth.codingchallenge.datalayer.repository.FactRepositoryImpl
import com.pelagohealth.codingchallenge.domain.repositories.FactRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataLayerModule {
	@Binds
	fun bindFactRepository(repository: FactRepositoryImpl): FactRepository
}
