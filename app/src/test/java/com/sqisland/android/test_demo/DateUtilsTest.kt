package com.sqisland.android.test_demo

import org.joda.time.DateTime
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.google.common.truth.Truth.assertThat

@RunWith(JUnit4::class)
class DateUtilsTest {
    @Test
    fun format() {
        assertThat(DateUtils.format(DateTime(2008, 9, 23, 0, 0, 0))).isEqualTo("2008-09-23")
    }
}