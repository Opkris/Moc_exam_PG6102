package no.mock.exam.enterprise

import no.mock.exam.enterprise.dto.CollectionDto
import no.mock.exam.enterprise.dto.Hall
import no.mock.exam.enterprise.dto.Hall.*
import no.mock.exam.enterprise.dto.MovieDto


object FakeData {

    fun getCollectionDto() : CollectionDto {

        val dto = CollectionDto()

        dto.prices[SMALL] = 100
        dto.prices[MEDIUM] = 120
        dto.prices[LARGE] = 150
        dto.prices[FourDX] = 210

        dto.prices.keys.forEach { dto.hallProbabilities[it] = 0.25 }

        dto.movies.run {
            add(MovieDto(movieId = "c00", hall = SMALL))
            add(MovieDto(movieId = "c01", hall = SMALL))
            add(MovieDto(movieId = "c02", hall = SMALL))
            add(MovieDto(movieId = "c03", hall = SMALL))
            add(MovieDto(movieId = "c04", hall = MEDIUM))
            add(MovieDto(movieId = "c05", hall = MEDIUM))
            add(MovieDto(movieId = "c06", hall = MEDIUM))
            add(MovieDto(movieId = "c07", hall = LARGE))
            add(MovieDto(movieId = "c08", hall = LARGE))
            add(MovieDto(movieId = "c09", hall = FourDX))
        }

        return dto
    }
}