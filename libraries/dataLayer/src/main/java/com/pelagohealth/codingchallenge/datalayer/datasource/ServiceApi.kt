package com.pelagohealth.codingchallenge.datalayer.datasource

import com.pelagohealth.codingchallenge.datalayer.entities.Response
import kotlinx.coroutines.flow.Flow


interface ServiceApi {
	suspend fun getFact(): Flow<Response>
}
