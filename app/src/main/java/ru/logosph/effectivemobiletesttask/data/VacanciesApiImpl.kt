package ru.logosph.effectivemobiletesttask.data

import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.logosph.effectivemobiletesttask.domain.interfaces.VacanciesApi
import ru.logosph.effectivemobiletesttask.domain.models.Response

class VacanciesApiImpl(val client: OkHttpClient) : VacanciesApi {

    companion object {
        private const val VACANCIES_URL =
            "https://drive.usercontent.google.com/u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download"
    }

    override suspend fun loadAllData(): Response? {
        val request = Request.Builder()
            .url(VACANCIES_URL)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw Exception("Unexpected code $response")
            return parseResponse(response.body!!.string())
        }
    }

    private fun parseResponse(response: String): Response? {
        val json = Json { ignoreUnknownKeys = true }
        val  parsedResponse = json.decodeFromString<Response>(response)
        return parsedResponse
    }

}