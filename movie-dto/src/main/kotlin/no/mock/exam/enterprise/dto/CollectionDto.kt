package no.mock.exam.enterprise.dto

class CollectionDto(

        var movies : MutableList<MovieDto> = mutableListOf(),

        var prices : MutableMap<Hall, Int> = mutableMapOf(),

        var hallProbabilities: MutableMap<Hall, Double> = mutableMapOf()

)