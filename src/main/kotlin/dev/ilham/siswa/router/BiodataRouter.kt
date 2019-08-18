package dev.ilham.siswa.router

import dev.ilham.siswa.handler.BiodataHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class BiodataRouter(val biodataHandler: BiodataHandler) {

    @Bean
    fun BiodataRoute() = router() {
        ("/biodata" and accept(MediaType.APPLICATION_JSON)).nest {
            POST("", biodataHandler::tambahBiodata)
            GET("", biodataHandler::ambilBiodata)
            DELETE("/{id}", biodataHandler::hapusBiodata)
            PUT("/{id}", biodataHandler::ubahBiodata)
            GET("/{id}", biodataHandler::cariBiodata)
        }
    }
}