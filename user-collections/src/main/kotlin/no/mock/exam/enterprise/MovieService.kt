package no.mock.exam.enterprise

import no.mock.exam.enterprise.dto.Hall
import no.mock.exam.enterprise.model.Movie
import no.mock.exam.enterprise.model.Collection
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import javax.annotation.PostConstruct
import kotlin.random.Random

@Service
class MovieService {

    companion object {
        private val logger = LoggerFactory.getLogger(MovieService::class.java)
    }

    protected var collection: Collection? = null

    val movieCollection: List<Movie>
        get() = collection?.movies ?: listOf()

    private val lock = Any()

    @PostConstruct
    fun init() {

        synchronized(lock) {
            if (movieCollection.isNotEmpty()) {
                return
            }
            fetchData()
        }
    }

    fun isInitialized() = movieCollection.isEmpty()

    protected fun fetchData() {
        // TODO
    }

    private fun verifyCollection() {

        if (collection == null) {
            fetchData()

            if (collection == null) {
                throw IllegalArgumentException("No Collection information")
            }
        }
    }

    fun price(movieId: String): Int {
        verifyCollection()
        val movie: Movie = movieCollection.find { it.movieId == movieId }
                ?: throw IllegalArgumentException("Invalid movieId $movieId")

        return collection!!.prices[movie.hall]!!
    }

    fun getRandomSelection(n: Int): List<Movie> {

        if (n <= 0) {
            throw IllegalArgumentException("Non-positive n: $n")
        }

        verifyCollection()

        val selection = mutableListOf<Movie>()

        val probabilities = collection!!.hallProbabilities
        val small = probabilities[Hall.SMALL]!!
        val medium = probabilities[Hall.MEDIUM]!!
        val large = probabilities[Hall.LARGE]!!
        val fourDX = probabilities[Hall.FourDX]!!

        repeat(n){
            val p = Math.random()
            val r = when {
                p <= small -> Hall.SMALL
                p > small && p <= small + medium -> Hall.MEDIUM
                p > small + medium && p <= small + medium + large -> Hall.LARGE
                p > small + medium + large -> Hall.FourDX
                else -> throw IllegalStateException("BUG for p=$p")
            }
            val movie = collection!!.moviesByHall[r].let{ it!![Random.nextInt(it.size)]}
        }
        return selection

    }
} // end class MovieService