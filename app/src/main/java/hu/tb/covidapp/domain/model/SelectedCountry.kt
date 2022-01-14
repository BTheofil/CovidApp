package hu.tb.covidapp.domain.model

data class SelectedCountry(
    val country: String,
    val confirmed: Int,
    val critical: Int,
    val deaths: Int,
    val recovered: Int
)
