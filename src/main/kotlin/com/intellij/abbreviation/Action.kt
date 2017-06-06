package com.intellij.abbreviation

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document

class ExpandAbbreviation : AnAction() {

    init {
        templatePresentation.text = "Expand Abbreviation"
        templatePresentation.description = "Using Experimental Abbreviations"
    }

    companion object {
        private val WS = listOf('\n', '\t', ' ').toCharArray()
    }

    override fun update(e: AnActionEvent) {
        val project = e.project
        val editor = e.dataContext.getData(CommonDataKeys.EDITOR)
        e.presentation.isEnabled = editor != null && project != null
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project!!
        val editor = e.dataContext.getData(CommonDataKeys.EDITOR)!!

        val document = editor.document
        val caretOffset = editor.caretModel.offset

        val chars = document.immutableCharSequence

        val abbrStartOffset = document.tokenStartOffset(caretOffset)
        val abbrText = chars.subSequence(abbrStartOffset, caretOffset)

        val newText = AbbreviationService.instance.suggestLine(abbrText) ?: return

        WriteCommandAction.runWriteCommandAction(project, Runnable {
            document.replaceString(abbrStartOffset, caretOffset, newText)
        })
    }


    private fun Document.tokenStartOffset(before: Int): Int {
        val chars = immutableCharSequence

        val lineNumber = getLineNumber(before)
        val lineNumberStartOffset = getLineStartOffset(lineNumber)

        val line = chars.subSequence(lineNumberStartOffset, before)
        val lastWhiteSpace = line.lastIndexOfAny(WS)

        return lineNumberStartOffset + lastWhiteSpace + 1
    }

}