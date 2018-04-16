package application


//import domain.*
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@TypeAlias("ServerEvent")
sealed class ServerEvent

@Document
@TypeAlias("UserRegistered")
data class UserRegistered
(
    @Indexed val something: String

): ServerEvent()

@Repository
interface ServerEventRepository: ReactiveCrudRepository<ServerEvent, String>

@RestController
@RequestMapping
class Controller(private val serverEventRepository: ServerEventRepository)
{
    @GetMapping
    fun get(): Mono<Int>
    {
        // You should already have at least one item in the database
        //
        // serverEventRepository.save(UserRegistered("lala")).subscribe()

        return serverEventRepository.findAll().collectList().map { it.size }
    }
}