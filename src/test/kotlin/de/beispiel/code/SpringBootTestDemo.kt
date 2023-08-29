package de.beispiel.code

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.ResponseEntity
import org.springframework.test.context.TestConstructor
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SpringBootTestDemo(
    private val restTemplate: TestRestTemplate,
) {
    @LocalServerPort
    private val port = 0

    @Test
    fun greetingShouldReturnDefaultMessageForGet() {
        val resEntity: ResponseEntity<String> = restTemplate.getForEntity("http://localhost:$port/")

        resEntity.statusCode.let {
            assertTrue(it.is2xxSuccessful, "response statusCode should be 2xx, but is ${it.value()}")
        }
        assertEquals("hello", resEntity.body)
    }

    @Test
    fun greetingShouldReturnMessageForPost() {
        val body = NameDto(firstName = "John", lastName = "Doe")
        val resEntity: ResponseEntity<String> = restTemplate.postForEntity("http://localhost:$port/", body)

        resEntity.statusCode.let {
            assertTrue(it.is2xxSuccessful, "response statusCode should be 2xx, but is ${it.value()}")
        }
        assertEquals("hello John Doe", resEntity.body)
    }
}

