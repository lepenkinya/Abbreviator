package com.intellij.abbreviation

import com.intellij.ide.highlighter.JavaFileType
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase

class ReplaceAbbreviationTest: LightCodeInsightFixtureTestCase() {

    override fun setUp() {
        super.setUp()
        TestSuggester.suggestFn = { "PsiDocumentManager manager = file.getManager();" }
    }

    fun `test simple replace`() {
        myFixture.configureByText(JavaFileType.INSTANCE, """
class Test {
    public void test() {
        psdm m = sdf.sdf<caret>
    }
}
""")

        myFixture.performEditorAction("ExpandAbbreviation")
        myFixture.checkResult("""
class Test {
    public void test() {
        PsiDocumentManager manager = file.getManager();
    }
}
""")
    }


    fun `test broken abbr`() {
        myFixture.configureByText(JavaFileType.INSTANCE, """
class Test {
    public void test() {
psdm m = sdf.sdf<caret>
    }
}
""")

        myFixture.performEditorAction("ExpandAbbreviation")
        myFixture.checkResult("""
class Test {
    public void test() {
        PsiDocumentManager manager = file.getManager();
    }
}
""")
    }

}