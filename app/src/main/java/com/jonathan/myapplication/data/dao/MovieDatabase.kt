package com.jonathan.myapplication.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jonathan.myapplication.data.model.Movies
import com.jonathan.myapplication.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Movies::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun getMovieAppDao(): MovieDao

    class Callback @Inject constructor(
        private val databaseMovies: Provider<MovieDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()
}