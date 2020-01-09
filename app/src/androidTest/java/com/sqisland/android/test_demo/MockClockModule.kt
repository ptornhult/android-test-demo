package com.sqisland.android.test_demo

import dagger.Module
import dagger.Provides
import io.mockk.every
import io.mockk.mockk
import org.joda.time.DateTime
import javax.inject.Singleton

@Module
class MockClockModule {
    @Provides
    @Singleton
    internal fun provideClock(): Clock {
        return mockk() {
            every { getNow() } returns DateTime()
        }
    }
}