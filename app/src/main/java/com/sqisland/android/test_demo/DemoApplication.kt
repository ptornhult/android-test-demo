package com.sqisland.android.test_demo

import android.app.Application
import android.content.Context
import com.sqisland.android.test_demo.db.DbModule
import dagger.Component
import javax.inject.Singleton

open class DemoApplication : Application() {

    private lateinit var component: DemoComponent

    override fun onCreate() {
        super.onCreate()

        component = createComponent(applicationContext)
    }

    @Singleton
    @Component(modules = [ClockModule::class, DbModule::class])
    interface ApplicationComponent : DemoComponent

    protected open fun createComponent(context: Context): DemoComponent = DaggerDemoComponent.builder()
            .dbModule(DbModule(context))
            .build()

    fun component(): DemoComponent = component
}