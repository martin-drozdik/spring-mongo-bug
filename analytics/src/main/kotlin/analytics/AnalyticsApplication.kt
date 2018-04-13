package analytics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
class AnalyticsApplication

fun main(vararg args: String)
{
    runApplication<AnalyticsApplication>(*args)
}