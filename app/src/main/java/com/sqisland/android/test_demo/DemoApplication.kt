package com.sqisland.android.test_demo

import android.app.Application

import javax.inject.Singleton

import dagger.Component

open class DemoApplication : Application() {

    private val component = createComponent()

    @Singleton
    @Component(modules = [ClockModule::class])
    interface ApplicationComponent : DemoComponent

    protected open fun createComponent(): DemoComponent = DaggerDemoComponent.builder().build()

    fun component(): DemoComponent = component
}