package com.hunseong.pokedex_clone.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.hunseong.pokedex_clone.db.AppDatabase
import com.hunseong.pokedex_clone.db.PokemonDao
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DBModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "Pokedex.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(appDatabase: AppDatabase) : PokemonDao {
        return appDatabase.pokemonDao()
    }
}