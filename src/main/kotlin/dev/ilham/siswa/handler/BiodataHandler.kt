package dev.ilham.siswa.handler

import dev.ilham.siswa.document.Biodata
import dev.ilham.siswa.repository.BiodataRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import reactor.core.publisher.Mono
import java.net.URI

@Component
class BiodataHandler(private val biodataRepository: BiodataRepository) {

    fun tambahBiodata(serverRequest: ServerRequest) =
            serverRequest.bodyToMono(Biodata::class.java)
                    .flatMap { this.biodataRepository.save(it) }
                    .flatMap { (id) -> created(URI.create("/biodata/"+id)).build() }

    fun ambilBiodata(serverRequest: ServerRequest) =
            ServerResponse.ok()
                    .body(biodataRepository.findAll(), Biodata::class.java)

    fun hapusBiodata(serverRequest: ServerRequest) =
            this.biodataRepository.findById(serverRequest.pathVariable("id"))
                    .flatMap { noContent().build(this.biodataRepository.delete(it)) }
                    .switchIfEmpty(notFound().build())

    fun ubahBiodata(serverRequest: ServerRequest) =
            serverRequest.bodyToMono(Biodata::class.java).flatMap { biodataBaru ->
                this.biodataRepository.findById(biodataBaru.id.toString())
                        .flatMap { this.biodataRepository.save(biodataBaru).then(ok().build()) }
                        .switchIfEmpty(badRequest().build())
            }

    fun cariBiodata(serverRequest: ServerRequest) =
            this.biodataRepository.findById(serverRequest.pathVariable("id"))
                    .flatMap { data -> ok().body(Mono.just(data), Biodata::class.java) }
                    .switchIfEmpty(notFound().build())
}