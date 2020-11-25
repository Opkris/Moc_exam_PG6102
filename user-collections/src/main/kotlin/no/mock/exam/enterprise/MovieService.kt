package no.mock.exam.enterprise

import no.mock.exam.enterprise.model.Movie
import no.mock.exam.enterprise.model.Collection
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import javax.annotation.PostConstruct

@Service
class MovieService {

    companion object{
        private val logger = LoggerFactory.getLogger(MovieService::class.java)
    }

    protected var collection: Collection? = null

    val movieCollection : List<Movie>
    get() = collection?.movies ?: listOf()

    private val lock = Any()

    @PostConstruct
    fun init(){

        synchronized(lock){
            if(movieCollection.isNotEmpty()){
                return
            }
            fetchData()
        }
    }

    fun isInitialized() = movieCollection.isEmpty()

    protected fun fetchData(){
        // TODO
    }

    private fun verifyCollection(){

        if(collection == null){
            fetchData()

            if(collection == null){
                throw IllegalArgumentException("No Collection information")
            }
        }
    }

    fun price(movieId: String) : Int {
        verifyCollection()
        val movie : Movie = movieCollection.find { it.movieId == movieId} ?:
                throw IllegalArgumentException("Invalid movieId $movieId")

        return collection!!.prices[movie.hall]!!
    }
}