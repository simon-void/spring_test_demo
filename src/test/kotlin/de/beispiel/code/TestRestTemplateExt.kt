package de.beispiel.code

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity

inline fun <reified T> TestRestTemplate.getForEntity(url: String): ResponseEntity<T> = this.getForEntity(url, T::class.java)
inline fun <reified T> TestRestTemplate.postForEntity(url: String, body: Any?): ResponseEntity<T> = this.postForEntity(url, body, T::class.java)