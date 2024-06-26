package com.pelagohealth.codingchallenge.datasource.api

import com.pelagohealth.codingchallenge.datasource.entities.FactEntity
import retrofit2.http.GET

/**
 * REST API for fetching random facts.
 */
interface FactsRestApi {
    @GET("facts/random")
    suspend fun getFactRandom(): FactEntity

}
