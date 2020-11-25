package no.mock.exam.enterprise.db

import no.mock.exam.enterprise.MovieService
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.LockModeType

@Repository
interface UserRepository : CrudRepository<User, String>{

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT user FROM User user WHERE user.userId = :id")
    fun lockedFind(@Param("id") userId: String) : User?
}

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


}