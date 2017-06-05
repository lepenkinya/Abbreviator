package sample

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class SampleAction : AnAction() {

    companion object {
        private val TITLE = "Sample Action"
        private val MESSAGE_TEXT = "Sample Action Hello!"
    }

    init {
        templatePresentation.text = "Sample Action"
        templatePresentation.description = "Show the Balloon"
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val notification = Notification(TITLE, TITLE, MESSAGE_TEXT, NotificationType.INFORMATION)
        notification.notify(project)
    }

}