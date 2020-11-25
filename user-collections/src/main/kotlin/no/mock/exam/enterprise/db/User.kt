package no.mock.exam.enterprise.db

import javax.persistence.*
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

    @get:OneToMany(mappedBy ="user", cascade = [(CascadeType.ALL)])
    var movieTickets : MutableList<MovieTickets> = mutableListOf()
}