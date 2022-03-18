package com.jonathan.myapplication.di

import android.app.Application
import androidx.room.Room
import com.jonathan.myapplication.data.dao.MovieDao
import com.jonathan.myapplication.data.dao.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: MovieDatabase.Callback): MovieDatabase {
        return Room.databaseBuilder(application, MovieDatabase::class.java, "alpha_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideMovieAppDao(movieAppDatabase: MovieDatabase): MovieDao {
        return movieAppDatabase.getMovieAppDao()
    }
}