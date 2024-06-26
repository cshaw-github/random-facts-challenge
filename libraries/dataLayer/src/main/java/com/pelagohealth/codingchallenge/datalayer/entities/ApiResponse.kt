package com.pelagohealth.codingchallenge.datalayer.entities

data class Response(
	val data: FactEntity? = null,
	val successful: Boolean = data != null,
	val error: String? = null,
) {
	companion object {
		fun success(
			data: FactEntity
		): Response = Response(data = data)

		fun error(
			errorString: String
		) = Response(error = errorString)
	}
}
