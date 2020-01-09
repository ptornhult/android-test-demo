package com.sqisland.android.test_demo.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is

class CounterDatabaseTest {
    private lateinit var counterDao: CounterDao
    private lateinit var db: CounterDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, CounterDatabase::class.java).build()
        counterDao = db.counterDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun getCounter_should_return_null_on_new_database() = runBlocking {
        assertThat(counterDao.getCounter(), Is(nullValue()))
    }

    @Test
    fun getCounter_should_return_counter_when_it_exists() = runBlocking {
        val counter = Counter(count = 42)
        counterDao.insert(counter)
        assertThat(counterDao.getCounter(), Is(not(nullValue())))
        assertThat(counterDao.getCounter()?.count, Is(counter.count))
    }

    @Test
    fun save_should_should_always_insert_or_replace_one_counter() = runBlocking {
        counterDao.save(Counter(count = 10))
        assertThat(counterDao.getCounter()?.count, Is(10))

        counterDao.save(Counter(count = 20))
        assertThat(counterDao.getCounter()?.count, Is(20))
    }
}