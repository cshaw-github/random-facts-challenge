package com.pelagohealth.codingchallenge.presentation.di

import com.pelagohealth.codingchallenge.domain.usecase.GetFactUseCase
import com.pelagohealth.codingchallenge.domain.usecase.GetFactUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainLayerModule {
	@Binds
	fun bindGetFactUseCase(useCase: GetFactUseCaseImpl): GetFactUseCase
}
