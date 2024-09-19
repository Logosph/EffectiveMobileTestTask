package ru.logosph.effectivemobiletesttask.data.favorite

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import ru.logosph.effectivemobiletesttask.domain.models.Address


class AddressConverter {

    val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromAddress(address: Address) = json.encodeToString(Address.serializer(), address)

    @TypeConverter
    fun toAddress(addressString: String) = json.decodeFromString<Address>(addressString)

}