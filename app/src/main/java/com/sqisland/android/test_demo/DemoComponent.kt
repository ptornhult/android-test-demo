package com.sqisland.android.test_demo

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [ClockModule::class])
interface DemoComponent {
    fun inject(mainActivity: MainActivity)
}