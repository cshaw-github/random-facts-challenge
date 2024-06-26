package com.pelagohealth.codingchallenge.datasource.di
import com.pelagohealth.codingchallenge.datalayer.datasource.ServiceApi
import com.pelagohealth.codingchallenge.datasource.api.FactsRestApi
import com.pelagohealth.codingchallenge.datasource.api.ServiceApiImpl
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

//@Module
//@InstallIn(SingletonComponent::class)
//object Module {
//	private const val BASE_URL = "https://uselessfacts.jsph.pl/api/v2"
//
//	@Provides
//	fun provideMoshi(): Moshi = Moshi.Builder().build()
//
//	@Provides
//	@Singleton
//	fun provideOkHttpClient(): OkHttpClient {
//		return OkHttpClient.Builder().build()
//	}
//
//	@Provides
//	@Singleton
//	fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
//		return MoshiConverterFactory.create(moshi)
//	}
//
//	@Provides
//	@Singleton
//	fun provideRetrofit(
//		okHttpClient: OkHttpClient,
//		moshiConverterFactory: MoshiConverterFactory,
//	): Retrofit {
//		return Retrofit.Builder()
//			.client(okHttpClient)
//			.addConverterFactory(moshiConverterFactory)
//			.baseUrl(BASE_URL)
//			.build()
//	}
//
//	@Provides
//	@Singleton
//	fun provideFactsRestApi(
//		retrofit: Retrofit
//	): FactsRestApi = retrofit.create(FactsRestApi::class.java)
//}
//
//@Module
//@InstallIn(SingletonComponent::class)
//internal interface ServiceApiModule {
//	@Binds
//	abstract fun bindServiceApi(serviceApi: ServiceApiImpl): ServiceApi
//}
//
//

val dataSourceModule = module {
	 val baseUrl = "https://uselessfacts.jsph.pl/api/v2/"

	single<Moshi> { Moshi.Builder().build() }
	single<MoshiConverterFactory> {
		val moshi: Moshi = get<Moshi>()
		MoshiConverterFactory.create(moshi)
	}
	single<OkHttpClient> { OkHttpClient.Builder().build() }
	single<Retrofit> {
		val httpClient: OkHttpClient = get<OkHttpClient>()
		Retrofit.Builder()
			.client(httpClient)
			.addConverterFactory(GsonConverterFactory.create())
			.baseUrl(baseUrl)
			.build()
	}
	single<FactsRestApi> {
		val retrofit = get<Retrofit>()
		retrofit.create(FactsRestApi::class.java)
	}
	single<ServiceApi> {
		ServiceApiImpl(get())
	}
}

