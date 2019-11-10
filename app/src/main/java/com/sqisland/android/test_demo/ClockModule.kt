package com.sqisland.android.test_demo

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class ClockModule {
    @Provides
    @Singleton
    internal fun provideClock(): Clock = Clock()
}