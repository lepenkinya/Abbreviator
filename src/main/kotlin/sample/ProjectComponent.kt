package sample

import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.project.Project

class SampleProjectComponent(val project: Project): ProjectComponent {

    override fun projectClosed() {
        println("Project ${project.name} closed")
    }

    override fun projectOpened() {
        println("Project ${project.name} opened")
    }

    override fun getComponentName() = "Sample Project Component"

    override fun disposeComponent() {}

    override fun initComponent() {}

}
