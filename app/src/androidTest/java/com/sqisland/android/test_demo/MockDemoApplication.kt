package com.sqisland.android.test_demo

import android.content.Context
import com.sqisland.android.test_demo.db.MockDbModule

class MockDemoApplication : DemoApplication() {
    override fun createComponent(context: Context): DemoComponent {
        return DaggerMainActivityTest_TestComponent.builder()
                .mockDbModule(MockDbModule(context))
                .build()
    }
}