package com.sqisland.android.test_demo

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.sqisland.android.test_demo.db.MockDbModule
import dagger.Component
import io.mockk.every
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton

class MainActivityTest {
    @Inject
    lateinit var clock: Clock

    @get:Rule
    var activityRule = ActivityTestRule(
            MainActivity::class.java,
            true, // initialTouchMode
            false)   // launchActivity. False so we can customize the intent per test method

    @Singleton
    @Component(modules = [MockClockModule::class, MockDbModule::class])
    interface TestComponent : DemoComponent {
        fun inject(mainActivityTest: MainActivityTest)
    }

    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as DemoApplication
        val component = app.component() as TestComponent
        component.inject(this)
    }

    @Test
    fun today() {
        every { clock.getNow() } returns DateTime(2008, 9, 23, 0, 0, 0)

        activityRule.launchActivity(Intent())

        onView(withId(R.id.date))
                .check(matches(withText("2008-09-23")))
    }

    @Test
    fun intent() {
        val dateTime = DateTime(2014, 10, 15, 0, 0, 0)
        val intent = Intent()
        intent.putExtra(MainActivity.KEY_MILLIS, dateTime.millis)

        activityRule.launchActivity(intent)

        onView(withId(R.id.date))
                .check(matches(withText("2014-10-15")))
    }
}