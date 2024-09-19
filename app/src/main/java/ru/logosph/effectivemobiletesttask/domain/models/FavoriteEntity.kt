package ru.logosph.effectivemobiletesttask.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = false) val id: String = "",
    val lookingNumber: Int? = null,
    val title: String? = null,
    val address: Address? = null,
    val company: String? = null,
    val experience: Experience? = null,
    val publishedDate: String? = null,
    val salary: Salary? = null,
    val schedules: List<String>? = null,
    val appliedNumber: Int? = null,
    val description: String? = null,
    val responsibilities: String? = null,
    val questions: List<String>? = null
)
