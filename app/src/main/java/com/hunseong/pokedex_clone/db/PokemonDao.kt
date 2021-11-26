package com.hunseong.pokedex_clone.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hunseong.pokedex_clone.model.Pokemon

// Room을 사용하는 이유 : Local DB에 최초 network에서 받아온 Pokemon List를 넣음으로써 캐싱을 통한 빠른 데이터 로드를 위해
// Networking보다 로컬 DB에서 값을 가져오는 게 훨씬 빠름

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<Pokemon>)


    // get하고자 하는 페이지의 데이터까지 local db에 저장되어 있는지 return 값의 isEmpty 여부로 판단
    @Query("SELECT * FROM Pokemon WHERE page = :page_")
    suspend fun getPokemonList(page_: Int): List<Pokemon>


    // getPokemonList에서 isNotEmpty라면 해당 method를 통해 해당 페이지까지의 모든 Pokemon 데이터를 get
    @Query("SELECT * FROM Pokemon WHERE page <= :page_")
    suspend fun getAllPokemonList(page_: Int): List<Pokemon>
}