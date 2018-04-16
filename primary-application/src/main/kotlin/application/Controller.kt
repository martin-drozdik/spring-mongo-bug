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
    init
    {
        // In order for the bug to manifest itself, there has to be at least one item
        // in the database.
        serverEventRepository.deleteAll().subscribe()
        serverEventRepository.save(UserRegistered("lala")).subscribe()
    }

    @GetMapping
    fun get(): Mono<Int>
    {
        // The bug only manifests itself with functions that construct the UserRegistered object.
        // Therefore I use this contrived example as opposed to 'serverEventRepository.count()'.

        return serverEventRepository.findAll().collectList().map { it.size }
    }
}
