package com.intellij.abbreviation

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.util.text.CharArrayUtil

class ExpandAbbreviation : AnAction() {

    init {
        templatePresentation.text = "Expand Abbreviation"
        templatePresentation.description = "Using Experimental Abbreviations"
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

        val abbrStartOffset = document.abbrStartOffset(caretOffset)
        if (abbrStartOffset == caretOffset) return
        val abbrText = chars.subSequence(abbrStartOffset, caretOffset)

        val newText = AbbreviationSuggester.instance.suggestLine(abbrText) ?: return

        WriteCommandAction.runWriteCommandAction(project, Runnable {
            document.replaceString(abbrStartOffset, caretOffset, newText)
        })
    }


    private fun Document.abbrStartOffset(beforeOffset: Int): Int {
        val lineNumber = getLineNumber(beforeOffset)
        val startOffset = getLineStartOffset(lineNumber)
        val abbrStartOffset = CharArrayUtil.shiftForward(immutableCharSequence, startOffset, beforeOffset, " \n\t")
        return abbrStartOffset
    }

}