package com.sqisland.android.test_demo.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MockDbModule(private val context: Context) {
    @Provides
    @Singleton
    internal fun provideDb(): CounterDatabase {
        return Room.inMemoryDatabaseBuilder(
                context, CounterDatabase::class.java).build()
    }
}