package backend.server.events

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

abstract class ServerEvent

@Document
data class UserRegistered
(
    @Indexed val something: String

): ServerEvent()
