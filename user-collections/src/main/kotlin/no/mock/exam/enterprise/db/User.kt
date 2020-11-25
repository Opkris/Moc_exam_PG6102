package no.mock.exam.enterprise.db

import org.checkerframework.common.aliasing.qual.Unique
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table (name = "Users")
class User {

    @get:Id
    @get:NotBlank
    var userId: String? = null

    @get:NotBlank
    @get:NotNull
    var userEmail: String? = null

    @get:Min(0)
    var cash: Int = 0

    @get:OneToMany(mappedBy ="user", cascade = [(CascadeType.ALL)])
    var ownedMovieTickets : MutableList<MovieTickets> = mutableListOf()
}