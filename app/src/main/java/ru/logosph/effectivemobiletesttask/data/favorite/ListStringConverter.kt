package ru.logosph.effectivemobiletesttask.data.favorite

import androidx.room.TypeConverter
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

class ListStringConverter {

    val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromListString(list: List<String>): String {
        return json.encodeToString(ListSerializer(String.serializer()), list)
    }

    @TypeConverter
    fun toListString(listString: String): List<String> {
        return json.decodeFromString(ListSerializer(String.serializer()), listString)
    }
}