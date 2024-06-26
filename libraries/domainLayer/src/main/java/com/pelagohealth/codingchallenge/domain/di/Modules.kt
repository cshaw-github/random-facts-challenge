package com.pelagohealth.codingchallenge.domain.di

import com.pelagohealth.codingchallenge.domain.usecase.GetFactUseCase
import com.pelagohealth.codingchallenge.domain.usecase.GetFactUseCaseImpl
import org.koin.dsl.module

//@Module
//@InstallIn(ViewModelComponent::class)
//internal interface Module {
//	@Binds
//	fun bindGetFactUseCase(useCase: GetFactUseCaseImpl): GetFactUseCase
//}

val domainLayerModule = module {
	factory<GetFactUseCase> { GetFactUseCaseImpl(get()) }
}
