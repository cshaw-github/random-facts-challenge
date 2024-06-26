package com.pelagohealth.codingchallenge.domain.usecase

import com.pelagohealth.codingchallenge.domain.entities.Status
import kotlinx.coroutines.flow.Flow

interface GetFactUseCase {
	suspend operator fun invoke(): Flow<Status>
}
