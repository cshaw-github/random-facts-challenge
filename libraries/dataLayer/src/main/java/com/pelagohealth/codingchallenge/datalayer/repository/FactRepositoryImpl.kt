package com.pelagohealth.codingchallenge.datalayer.repository

import com.pelagohealth.codingchallenge.datalayer.datasource.ServiceApi
import com.pelagohealth.codingchallenge.datalayer.entities.Response
import com.pelagohealth.codingchallenge.datalayer.mapper.toDomainLayer
import com.pelagohealth.codingchallenge.domain.entities.Status
import com.pelagohealth.codingchallenge.domain.repositories.FactRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalCoroutinesApi::class)
internal class FactRepositoryImpl(
	private val serviceApi: ServiceApi,
) : FactRepository {

	override suspend fun getFact(): Flow<Status> {
		return serviceApi
			.getFact()
			.flatMapLatest(::validateAndRemoveDuplicates)
	}

	private fun validateAndRemoveDuplicates(
		response: Response
	): Flow<Status> {
		val status = if (response.successful) {
			// TODO: save to database

			Status(data = response.data?.toDomainLayer())
		} else {
			Status(error = response.error ?: "UNKNOWN_ERROR")
		}

		return flowOf(status)
	}
}
