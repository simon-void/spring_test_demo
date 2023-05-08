package de.beispiel.code

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@WebMvcTest
class WebMvcTestDemo(
    @Autowired val mockMvc: MockMvc,
) {
    @MockkBean
    lateinit var greetingService: GreetingService
    val mapper = jacksonObjectMapper()

    @Test
    fun `test get request to HelloController returns greetingService getGreetingFor(null) result with status 200`() {
        every { greetingService.getGreetingFor(isNull()) } returns "hi"

        mockMvc.perform(get("/"))
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
            .andExpect(content().string("hi"))
    }

    @Test
    fun `test post request to HelloController returns greetingService getGreetingFor(null) result with status 200`() {
        val nameDto = NameDto(firstName = "John", lastName = "Doe")
        every { greetingService.getGreetingFor(eq(nameDto)) } answers {"hi ${nameDto.firstName} ${nameDto.lastName}"}

        mockMvc.perform(post("/").content(mapper.writeValueAsString(nameDto)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
            .andExpect(content().string("hi John Doe"))
    }
}