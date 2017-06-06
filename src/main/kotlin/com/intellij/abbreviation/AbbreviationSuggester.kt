package com.intellij.abbreviation

import com.intellij.openapi.components.service


interface AbbreviationSuggester {
    fun suggestLine(abbreviation: CharSequence): String?

    companion object {
        val instance: AbbreviationSuggester
            get() = service()
    }
}


class AbbreviationSuggesterImpl(private val abbreviationStats: AbbreviationStats): AbbreviationSuggester {

    override fun suggestLine(abbreviation: CharSequence): String? {
        return abbreviationStats.data["suggestion"]
    }

}