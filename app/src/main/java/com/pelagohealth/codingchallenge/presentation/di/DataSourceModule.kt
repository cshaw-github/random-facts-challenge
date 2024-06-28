package com.pelagohealth.codingchallenge.presentation.di

import com.pelagohealth.codingchallenge.datalayer.datasource.ServiceApi
import com.pelagohealth.codingchallenge.datasource.api.FactsRestApi
import com.pelagohealth.codingchallenge.datasource.api.ServiceApiImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
	private const val BASE_URL = "https://uselessfacts.jsph.pl/api/v2/"

	@Provides
	@Singleton
	fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

	@Provides
	@Singleton
	fun provideRetrofit(
		okHttpClient: OkHttpClient,
	): Retrofit {
		return Retrofit.Builder()
			.client(okHttpClient)
			.addConverterFactory(GsonConverterFactory.create())
			.baseUrl(BASE_URL)
			.build()
	}

	@Provides
	@Singleton
	fun provideFactsRestApi(
		retrofit: Retrofit
	): FactsRestApi = retrofit.create(FactsRestApi::class.java)

	@Provides
	@Singleton
	fun providesServiceApi(factsRestApi: FactsRestApi): ServiceApi = ServiceApiImpl(factsRestApi)
}
