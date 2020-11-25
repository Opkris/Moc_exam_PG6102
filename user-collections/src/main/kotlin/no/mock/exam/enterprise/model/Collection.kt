package no.mock.exam.enterprise.model

import no.mock.exam.enterprise.dto.CollectionDto
import no.mock.exam.enterprise.dto.Hall

class Collection (

    val movies : List<Movie>,

    val prices: Map<Hall, Int>,

    val hallProbabilities: Map<Hall, Double>
){
    constructor(dto: CollectionDto) : this(
            dto.movies.map {Movie(it) },
            dto.prices.toMap(),
            dto.hallProbabilities.toMap()
    )

}

