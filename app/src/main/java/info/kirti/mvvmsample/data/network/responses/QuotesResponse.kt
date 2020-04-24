package info.kirti.mvvmsample.data.network.responses

import info.kirti.mvvmsample.data.db.entities.Quote


data class QuotesResponse(
    val isSuccessful: Boolean,
    val quotes :List<Quote>
)