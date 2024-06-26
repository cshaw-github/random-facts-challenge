package com.pelagohealth.codingchallenge.presentation.mapper

import com.pelagohealth.codingchallenge.domain.entities.FactEntity as DomainFact
import com.pelagohealth.codingchallenge.presentation.model.FactEntity as UiFact

fun DomainFact.toUi() = UiFact(
	text = this.text,
	sourceUrl = this.sourceUrl
)
