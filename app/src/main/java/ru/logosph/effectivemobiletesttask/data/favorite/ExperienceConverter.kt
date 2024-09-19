package ru.logosph.effectivemobiletesttask.data.favorite

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import ru.logosph.effectivemobiletesttask.domain.models.Experience

class ExperienceConverter {

    val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromExperience(experience: Experience) = json.encodeToString(Experience.serializer(), experience)

    @TypeConverter
    fun toExperience(experienceString: String) = json.decodeFromString<Experience>(experienceString)



}