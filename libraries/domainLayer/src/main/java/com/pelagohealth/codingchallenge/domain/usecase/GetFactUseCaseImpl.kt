package com.pelagohealth.codingchallenge.domain.usecase

import com.pelagohealth.codingchallenge.domain.entities.Status
import com.pelagohealth.codingchallenge.domain.repositories.FactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFactUseCaseImpl @Inject constructor(
	private val repository: FactRepository,
) : GetFactUseCase {
	override suspend fun invoke(): Flow<Status> = repository.getFact()
}
