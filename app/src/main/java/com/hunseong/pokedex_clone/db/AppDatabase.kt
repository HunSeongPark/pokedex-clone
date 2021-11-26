package com.hunseong.pokedex_clone.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hunseong.pokedex_clone.model.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}