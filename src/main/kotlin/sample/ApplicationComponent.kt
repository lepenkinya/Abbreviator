package sample

import com.intellij.openapi.components.ApplicationComponent

class SampleAppComponent: ApplicationComponent.Adapter() {

    override fun getComponentName() = "SampleAppComponent"

    override fun disposeComponent() {
        println("Disposed on app close")
    }

    override fun initComponent() {
        println("Initialized during app loading")
    }

}