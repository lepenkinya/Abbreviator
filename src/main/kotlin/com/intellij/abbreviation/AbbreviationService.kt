package com.intellij.abbreviation

import com.intellij.openapi.components.service


interface AbbreviationService {
    fun suggestLine(abbreviation: CharSequence): String?

    companion object {
        val instance: AbbreviationService
            get() = service()
    }
}


class AbbreviationServiceImpl: AbbreviationService {

    override fun suggestLine(abbreviation: CharSequence): String? {
        return "my.suggested.line.call(100);"
    }
}