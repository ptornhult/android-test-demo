package com.sqisland.android.test_demo.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Entity
data class Counter(@PrimaryKey val id: Int? = null, @ColumnInfo(name = "count") val count: Int = 0)

@Dao
abstract class CounterDao {
    @Query("SELECT * FROM counter LIMIT 1")
    abstract fun liveCounter(): LiveData<Counter>

    @Query("SELECT * FROM counter LIMIT 1")
    abstract suspend fun getCounter(): Counter?

    @Insert
    abstract suspend fun insert(counter: Counter)

    @Query("DELETE FROM counter")
    abstract suspend fun deleteAll()

    @Transaction
    open suspend fun save(counter: Counter) {
        deleteAll()
        insert(counter)
    }
}

@Database(entities = [Counter::class], version = 1)
abstract class CounterDatabase : RoomDatabase() {
    abstract fun counterDao(): CounterDao
}

@Module
class DbModule(private val context: Context) {
    @Provides
    @Singleton
    internal fun provideDb(): CounterDatabase =
            Room.databaseBuilder(context, CounterDatabase::class.java, "counter-database")
                    .build()
}