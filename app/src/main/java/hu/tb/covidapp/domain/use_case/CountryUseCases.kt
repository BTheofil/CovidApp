package hu.tb.covidapp.domain.use_case

data class CountryUseCases(
    val addCountry: AddCountry,
    val getDBCountries: GetDBCountries,
)
