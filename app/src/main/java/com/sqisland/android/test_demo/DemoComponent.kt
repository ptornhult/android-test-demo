package com.sqisland.android.test_demo

import com.sqisland.android.test_demo.db.DbModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ClockModule::class, DbModule::class])
interface DemoComponent {
    fun inject(mainActivity: MainActivity)
}