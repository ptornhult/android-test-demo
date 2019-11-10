package com.sqisland.android.test_demo

import org.joda.time.DateTime

import javax.inject.Singleton

@Singleton
class Clock {
    fun getNow(): DateTime = DateTime()
}