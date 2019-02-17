package ru.cityproblemsmap.api.model

data class SendPointData(
        val latitude: Double,
        val longitude: Double,
        val group: String,
        val status: String,
        val category: String,
        val description: String
//        val photoUrl: String? = ""
)

data class GetPointsResponse(
        val points: List<GetPointData>
)

data class GetPointData(
        val id: Long,
        val latitude: Double,
        val longitude: Double,
        val status: String,
        val group: String,
        val category: String,
        val description: String,
        val photoUrl: String
)

data class UploadImageResponse(
        val url: String
)