package dev.ilham.siswa.repository

import dev.ilham.siswa.document.Biodata
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BiodataRepository : ReactiveMongoRepository<Biodata, String>