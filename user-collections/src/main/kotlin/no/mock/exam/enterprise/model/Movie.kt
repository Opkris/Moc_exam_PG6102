package no.mock.exam.enterprise.model

import no.mock.exam.enterprise.dto.Hall
import no.mock.exam.enterprise.dto.MovieDto
import java.lang.IllegalArgumentException

class Movie(
        val movieId: String,
        val hall: Hall

) {
    constructor(dto: MovieDto): this(
            dto.movieId?: throw IllegalArgumentException("Null movieId"),
            dto.hall?: throw IllegalArgumentException("hall was not set"))
}