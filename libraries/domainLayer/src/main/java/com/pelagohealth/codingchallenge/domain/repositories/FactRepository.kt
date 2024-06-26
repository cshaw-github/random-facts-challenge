package com.pelagohealth.codingchallenge.domain.repositories

import com.pelagohealth.codingchallenge.domain.entities.Status
import kotlinx.coroutines.flow.Flow

interface FactRepository {
	suspend fun getFact(): Flow<Status>
}
