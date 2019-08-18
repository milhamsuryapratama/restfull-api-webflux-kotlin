package dev.ilham.siswa.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "biodata")
data class Biodata (
    @Id
    var id: String?,
    var nama: String? = "",
    var alamat: String? = ""
)