package com.sqisland.android.test_demo

import org.mockito.Mockito

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class MockClockModule {
    @Provides
    @Singleton
    internal fun provideClock(): Clock {
        return Mockito.mock(Clock::class.java)
    }
}