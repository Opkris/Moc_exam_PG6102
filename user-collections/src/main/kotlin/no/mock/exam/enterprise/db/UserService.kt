package no.mock.exam.enterprise.db

import no.mock.exam.enterprise.MovieService
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import javax.persistence.LockModeType

@Repository
interface UserRepository : CrudRepository<User, String>{

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT user FROM User user WHERE user.userId = :id")
    fun lockedFind(@Param("id") userId: String) : User?

    @Query("SELECT user FROM User user WHERE user.userEmail = :email")
    fun findUserWithEmail(@Param("email") userEmail: String) : User?

@Service
@Transactional
class UserService(
        private val userRepository: UserRepository,
        private val movieService: MovieService
){


    fun findUserByIdEager(userId: String) : User?{

        val user = userRepository.findById(userId).orElse(null)
        user?.movieTickets?.size

        return user
    }

    fun registerNewUser(userId: String, userEmail: String) : Boolean{

        val emailExist = userRepository.findUserWithEmail(userEmail);

        if(userRepository.existsById(userId)){
            return false
        }

        if(emailExist != null){
            return false
        }


        val user = User()
        user.userId = userId
        user.userEmail = userEmail
        user.cash = 500
        userRepository.save(user)
        return true
    }

    private fun validateMovie(movieId: String){
        if(!movieService.isInitialized()){
            throw IllegalStateException("Movie Service is not Initialized")
        }
    }

    private fun validateUser(userId: String){
        if(userRepository.existsById(userId)){
            throw IllegalArgumentException("User $userId does not exist")
        }
    }

    private fun validate(userId: String, movieId: String){
        validateUser(userId)
        validateMovie(movieId)
    }

    fun buyMovieTicket(userId: String, movieId: String){
        validate(userId, movieId)

        val price = movieService.price(movieId)
    }


} // end userService
} // end interface