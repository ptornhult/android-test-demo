package com.sqisland.android.test_demo;

import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
  @Inject
  Clock clock;

  @Singleton
  @Component(modules = MockClockModule.class)
  public interface TestComponent extends DemoComponent {
    void inject(MainActivityTest mainActivityTest);
  }

  @Rule
  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
      MainActivity.class,
      true,     // initialTouchMode
      false);   // launchActivity. False so we can customize the intent per test method

  @Before
  public void setUp() {
    Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    DemoApplication app
        = (DemoApplication) instrumentation.getTargetContext().getApplicationContext();
    TestComponent component = (TestComponent) app.component();
    component.inject(this);
  }

  @Test
  public void today() {
    Mockito.when(clock.getNow()).thenReturn(new DateTime(2008, 9, 23, 0, 0, 0));

    activityRule.launchActivity(new Intent());

    onView(withId(R.id.date))
        .check(matches(withText("2008-09-23")));
  }

  @Test
  public void intent() {
    DateTime dateTime = new DateTime(2014, 10, 15, 0, 0, 0);
    Intent intent = new Intent();
    intent.putExtra(MainActivity.KEY_MILLIS, dateTime.getMillis());

    activityRule.launchActivity(intent);

    onView(withId(R.id.date))
        .check(matches(withText("2014-10-15")));
  }
}