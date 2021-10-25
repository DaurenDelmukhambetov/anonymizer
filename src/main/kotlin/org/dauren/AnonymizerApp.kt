package org.dauren

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.dauren.model.Text

fun main() {
    val obfuscator = Obfuscator()
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            jackson()
        }
        routing {
            get("/") {
                call.respondText("Welcome to Anonymizer App!")
            }
            post("/anonymize") {
                val inputText = call.receive<Text>()
                val obfuscatedText = obfuscator.obfuscate(inputText.content);
                call.respond(HttpStatusCode.OK, Text(obfuscatedText))
            }
        }
    }.start(wait = true)
}
