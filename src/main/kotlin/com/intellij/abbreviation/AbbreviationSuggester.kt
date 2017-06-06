package com.intellij.abbreviation

import com.intellij.openapi.components.service


interface AbbreviationSuggester {
    fun suggestLine(abbreviation: CharSequence): CharSequence?

    companion object {
        val instance: AbbreviationSuggester
            get() = service()
    }
}


class AbbreviationSuggesterImpl(private val abbreviationStats: AbbreviationStats): AbbreviationSuggester {

    override fun suggestLine(abbreviation: CharSequence): CharSequence? {
        return abbreviationStats.data["suggestion"]
    }

}