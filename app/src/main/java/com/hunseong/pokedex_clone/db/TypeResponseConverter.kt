package com.hunseong.pokedex_clone.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.hunseong.pokedex_clone.model.PokemonInfo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class TypeResponseConverter @Inject constructor(
    private val moshi: Moshi
) {

    // 직렬화
    @TypeConverter
    fun fromString(value: String): List<PokemonInfo.TypeResponse>? {
        val listType = Types.newParameterizedType(List::class.java, PokemonInfo.TypeResponse::class.java)
        val adapter: JsonAdapter<List<PokemonInfo.TypeResponse>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    // 역직렬화
    @TypeConverter
    fun fromInfoType(type: List<PokemonInfo.TypeResponse>?): String {
        val listType = Types.newParameterizedType(List::class.java, PokemonInfo.TypeResponse::class.java)
        val adapter: JsonAdapter<List<PokemonInfo.TypeResponse>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}