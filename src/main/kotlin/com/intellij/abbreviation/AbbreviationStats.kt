package com.intellij.abbreviation


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.intellij.openapi.components.ApplicationComponent


class AbbreviationStats : ApplicationComponent {
    companion object {
        private val gson = Gson()
    }

    lateinit var data: SSMap

    override fun getComponentName() = "Abbreviation Storage"

    override fun disposeComponent() {}

    override fun initComponent() {
        val fileContent = resourceContent("data/suggester.json")
        val typeToken = object : TypeToken<SSMap>() {}
        data = gson.fromJson<SSMap>(fileContent, typeToken.type)
    }

}


typealias SSMap = Map<String, String>


fun resourceContent(path: String): String {
    return AbbreviationStats::class.java.classLoader
            .getResourceAsStream(path)
            .reader().readText()
}