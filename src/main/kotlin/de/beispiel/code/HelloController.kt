package de.beispiel.code

import org.springframework.web.bind.annotation.*


@RestController
class HelloController(
    private val greetingService: GreetingService,
) {
    @GetMapping("/")
    fun index(): String = greetingService.getGreetingFor(null)

    @PostMapping("/", consumes = ["application/json"])
    fun index(
        @RequestBody nameDto: NameDto,
    ): String = greetingService.getGreetingFor(nameDto)
}