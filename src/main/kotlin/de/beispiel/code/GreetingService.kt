package de.beispiel.code

import org.springframework.stereotype.Service

@Service
class GreetingService {
    fun getGreetingFor(nameDto: NameDto?): String = if(nameDto==null) {
        "hello"
    } else {
        "hello ${nameDto.firstName} ${nameDto.lastName}"
    }
}

data class NameDto (
    val firstName: String,
    val lastName: String,
)