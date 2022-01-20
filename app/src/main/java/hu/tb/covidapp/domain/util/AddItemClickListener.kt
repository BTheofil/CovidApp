package hu.tb.covidapp.domain.util

import hu.tb.covidapp.domain.model.Country

interface AddItemClickListener {
    fun addItemClick(country: Country)
}