package com.pelagohealth.codingchallenge.datalayer.mapper

import com.pelagohealth.codingchallenge.domain.entities.FactEntity as DomainFact;
import com.pelagohealth.codingchallenge.datalayer.entities.FactEntity as DataLayerFact;

fun DataLayerFact.toDomainLayer() = DomainFact(
	text = this.text,
	sourceUrl = this.sourceUrl,
)
