package com.sqisland.android.test_demo

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

import org.joda.time.DateTime

import javax.inject.Inject

class MainActivity : Activity() {

    @Inject
    lateinit var clock: Clock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as DemoApplication).component().inject(this)

        val todayView = findViewById<TextView>(R.id.date)

        val millis = intent.getLongExtra(KEY_MILLIS, -1)
        val dateTime = if (millis > 0) DateTime(millis) else clock.getNow()
        todayView.text = DateUtils.format(dateTime)
    }

    companion object {
        const val KEY_MILLIS = "millis"
    }
}