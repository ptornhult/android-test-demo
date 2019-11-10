package com.sqisland.android.test_demo

class MockDemoApplication : DemoApplication() {
    override fun createComponent(): DemoComponent {
        return DaggerMainActivityTest_TestComponent.builder().build()
    }
}