package domain

import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

// The following changes make the code work:
//
// 1. Remove the '@Indexed' annotation
// 2. Remove the '@Document' annotation
// 3. Add a '@TypeAlias' annotation. However this induces a new error:
//
// {"timestamp":"2018-04-16T15:01:59.002+0000","path":"/","status":500,"error":"Internal Server Error","message":"java.lang.IllegalAccessException: Class kotlin.reflect.jvm.internal.FunctionCaller$Constructor can not access a member of class domain.ServerEvent with modifiers \"private\""}

sealed class ServerEvent

@Document
//@TypeAlias("UserRegistered")    // Note: after uncommenting this annotation, remember to re-populate the DB
data class UserRegistered
(
    @Indexed val something: String

): ServerEvent()

