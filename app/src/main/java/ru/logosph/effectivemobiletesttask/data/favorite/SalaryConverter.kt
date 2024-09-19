package ru.logosph.effectivemobiletesttask.data.favorite

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import ru.logosph.effectivemobiletesttask.domain.models.Salary

class SalaryConverter {

    val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromSalary(salary: Salary) = json.encodeToString(Salary.serializer(), salary)

    @TypeConverter
    fun toSalary(salaryString: String) = json.decodeFromString<Salary>(salaryString)


}