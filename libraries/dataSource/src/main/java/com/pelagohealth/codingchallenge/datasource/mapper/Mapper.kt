package com.pelagohealth.codingchallenge.datasource.mapper

import com.pelagohealth.codingchallenge.datasource.entities.FactEntity as DataSourceFact;
import com.pelagohealth.codingchallenge.datalayer.entities.FactEntity as DataLayerFact;

fun DataSourceFact.toDataLayer() = DataLayerFact(
	text = this.text,
	sourceUrl = this.sourceUrl,
)
