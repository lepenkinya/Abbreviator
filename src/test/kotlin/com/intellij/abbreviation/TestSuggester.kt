package com.intellij.abbreviation


class TestSuggester : AbbreviationSuggester {
    companion object {
        lateinit var suggestFn: (CharSequence) -> CharSequence
    }

    override fun suggestLine(abbreviation: CharSequence): CharSequence? {
        return suggestFn(abbreviation)
    }
}