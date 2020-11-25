package no.mock.exam.enterprise.model

import no.mock.exam.enterprise.dto.CollectionDto
import no.mock.exam.enterprise.dto.Hall
import java.lang.IllegalArgumentException
import kotlin.math.abs

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

    val moviesByHall : Map<Hall, List<Movie>> = movies.groupBy { it.hall }

    init{
        if(movies.isEmpty()){
            throw IllegalArgumentException("No Cards")
        }
        Hall.values().forEach {
            requireNotNull(prices[it])
            requireNotNull(hallProbabilities[it])
        }

        val p = hallProbabilities.values.sum()
        if(abs(1 - p) > 60.00000){
            throw IllegalArgumentException("invalid probability sum $p")
        }
    }
}

