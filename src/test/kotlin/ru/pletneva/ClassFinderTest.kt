package ru.pletneva

import org.junit.Test
import kotlin.test.*

class ClassFinderTest {

    private val classFinder = ClassFinder(listOf("a.b.FooBarBaz", "c.d.FooBar"))

    @Test
    fun sortClasses() {
        assertEquals(
            mutableListOf("FooBar", "FooBarBaz"),
            classFinder.sortClasses(mutableListOf("FooBarBaz", "FooBar"))
        )
    }

    @Test
    fun `sortClasses ignore PackageName`() {
        assertEquals(
            mutableListOf("c.d.FooBar", "a.b.FooBarBaz"),
            classFinder.sortClasses(mutableListOf("a.b.FooBarBaz", "c.d.FooBar"))
        )
    }

    @Test
    fun `findMatchedClasses only lower case`() {
        assertEquals(
            listOf("c.d.FooBar", "a.b.FooBarBaz"),
            classFinder.findMatchedClasses("FB")
        )
    }

    @Test
    fun `findMatchedClasses upper and lower`() {
        assertEquals(
            listOf("c.d.FooBar", "a.b.FooBarBaz"),
            classFinder.findMatchedClasses("FoBa")
        )
    }

    @Test
    fun `findMatchedClasses Upper and next lower`() {
        assertEquals(
            listOf("c.d.FooBar", "a.b.FooBarBaz"),
            classFinder.findMatchedClasses("FoBa")
        )
    }

    @Test
    fun `findMatchedClasses skip words`() {
        assertEquals(
            listOf("a.b.FooBarBaz"),
            classFinder.findMatchedClasses("FooBaz")
        )
    }

    @Test
    fun `findMatchedClasses EndsWithSpace`() {
        assertEquals(
            listOf("c.d.FooBar"),
            classFinder.findMatchedClasses("Bar ")
        )
    }

    @Test
    fun `findMatchedClasses miss letter in part`() {
        assertEquals(
            emptyList(),
            classFinder.findMatchedClasses("BrBaz")
        )
    }

    @Test
    fun `findMatchedClasses sensitive`() {
        assertEquals(
            emptyList(),
            classFinder.findMatchedClasses("fB")
        )
    }

    @Test
    fun `findMatchedClasses not found insensitive`() {
        assertEquals(
            emptyList(),
            classFinder.findMatchedClasses("oao")
        )
    }

    @Test
    fun `findMatchedClasses insensitive sequence`() {
        assertEquals(
            listOf("c.d.FooBar", "a.b.FooBarBaz"),
            classFinder.findMatchedClasses("oba")
        )
    }

    @Test
    fun `findMatchedClasses insensitive endsWithSpace`() {
        assertEquals(
            listOf("c.d.FooBar"),
            classFinder.findMatchedClasses("or ")
        )
    }

    @Test
    fun `findMatchedClasses miss letter in part with wildcard`() {
        assertEquals(
            listOf("a.b.FooBarBaz"),
            classFinder.findMatchedClasses("B*rBaz")
        )
    }

    @Test
    fun `findMatchedClasses insensitive skip wildcard`() {
        assertEquals(
            listOf("c.d.FooBar", "a.b.FooBarBaz"),
            classFinder.findMatchedClasses("o********b*****************a")
        )
    }


    @Test
    fun `findMatchedClasses with wildcards skip few letters`() {
        assertEquals(
            listOf("a.b.FooBarBaz"),
            classFinder.findMatchedClasses("F*rBaz")
        )
    }

    @Test
    fun `findMatchedClasses with wildcards not found `() {
        assertEquals(
            emptyList(),
            classFinder.findMatchedClasses("M*z")
        )
        assertEquals(
            emptyList(),
            classFinder.findMatchedClasses("F*o ")
        )
    }

    @Test
    fun `findMatchedClasses with wildcards without skipping`() {
        assertEquals(
            listOf("a.b.FooBarBaz"),
            classFinder.findMatchedClasses("Ba*rBaz")
        )
    }

    @Test
    fun `findMatchedClasses with wildcards not found`() {
        assertEquals(
            emptyList(),
            classFinder.findMatchedClasses("B*rA")
        )
    }
}