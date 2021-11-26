package com.hunseong.pokedex_clone.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
@JsonClass(generateAdapter = true)
data class Pokemon(
    var page: Int = 0,
    @field:Json(name = "name") @PrimaryKey val name: String,
    @field:Json(name = "url") val url: String
) : Parcelable {

    // url의 형태가 https://pokeapi.co/api/v2/pokemon/1/ 과 같이 되어있음.
    // "/" 기준 split => [https:, , pokeapi.co, api, v2, pokemon, 1, ]
    // dropLast(1) 뒤의 element 1개 삭제 => [https:, , pokeapi.co, api, v2, pokemon, 1]
    // last() => 마지막 값 (index) 가져옴
    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}