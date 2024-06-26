package com.pelagohealth.codingchallenge.datasource.api

import com.pelagohealth.codingchallenge.datalayer.datasource.ServiceApi
import com.pelagohealth.codingchallenge.datalayer.entities.Response
import com.pelagohealth.codingchallenge.datasource.entities.FactEntity
import com.pelagohealth.codingchallenge.datasource.mapper.toDataLayer
import com.pelagohealth.codingchallenge.utils.Uitls.NO_NETWORK
import com.pelagohealth.codingchallenge.utils.Uitls.UNKNOWN_ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.withTimeoutOrNull
import java.net.ConnectException
import kotlin.time.Duration.Companion.seconds

internal class ServiceApiImpl(
	private val restApi: FactsRestApi
) : ServiceApi {
	override suspend fun getFact(): Flow<Response> = flow {
		try {
			val response = withTimeoutOrNull(TIMEOUT) {
				restApi.getFactRandom()
			}
			emit(processResponse(response))
		} catch (e: Exception) {
			emit(processResponse(exception = e))
		}
	}

	private fun processResponse(
		response: FactEntity? = null,
		exception: Exception? = null,
	): Response = if (response != null) {
		Response.success(response.toDataLayer())
	} else  {
		val error = when (exception) {
			is ConnectException -> NO_NETWORK
			else -> UNKNOWN_ERROR
		}
		Response.error(error)
	}

	private companion object {
		private val TIMEOUT = 10.seconds
	}
}
