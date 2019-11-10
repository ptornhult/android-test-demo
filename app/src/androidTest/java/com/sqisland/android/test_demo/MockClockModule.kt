package com.sqisland.android.test_demo

import dagger.Module
import dagger.Provides
import io.mockk.mockk
import javax.inject.Singleton

@Module
class MockClockModule {
    @Provides
    @Singleton
    internal fun provideClock(): Clock {
        return mockk()
    }
}