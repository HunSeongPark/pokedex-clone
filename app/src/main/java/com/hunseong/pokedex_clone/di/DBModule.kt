package com.hunseong.pokedex_clone.di

import android.content.Context
import androidx.room.Room
import com.hunseong.pokedex_clone.db.AppDatabase
import com.hunseong.pokedex_clone.db.PokemonDao
import com.hunseong.pokedex_clone.db.PokemonInfoDao
import com.hunseong.pokedex_clone.db.TypeResponseConverter
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
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        typeResponseConverter: TypeResponseConverter
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "Pokedex.db")
            .fallbackToDestructiveMigration()
            .addTypeConverter(typeResponseConverter)
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(appDatabase: AppDatabase): PokemonDao {
        return appDatabase.pokemonDao()
    }

    @Provides
    @Singleton
    fun providePokemonInfoDao(appDatabase: AppDatabase): PokemonInfoDao {
        return appDatabase.pokemonInfoDao()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideTypeResponseConverter(moshi: Moshi): TypeResponseConverter {
        return TypeResponseConverter(moshi)
    }
}