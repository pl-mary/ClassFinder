package ru.pletneva

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PatternTest {

    @Test
    fun `parsePattern sensitive`() {
        val pattern = parsePattern("FooBarBaz")
        assertEquals("Foo", pattern.words[0])
        assertEquals("Bar", pattern.words[1])
        assertEquals("Baz", pattern.words[2])
        assertFalse { pattern.endsWithSpace }
        assertFalse { pattern.ignoreCase }
    }

    @Test
    fun `parsePattern start with lower`() {
        val pattern = parsePattern("ooBar")
        assertEquals("oo", pattern.words[0])
        assertEquals("Bar", pattern.words[1])
        assertFalse { pattern.endsWithSpace }
        assertFalse { pattern.ignoreCase }
    }

    @Test
    fun `parsePattern insensitive`() {
        val pattern = parsePattern("o*******a")
        assertEquals("o", pattern.words[0])
        assertEquals("a", pattern.words[1])
        assertFalse { pattern.endsWithSpace }
        assertTrue { pattern.ignoreCase }
    }

    @Test
    fun `parsePattern insensitive or`() {
        val pattern = parsePattern("or ")
        assertEquals("o", pattern.words[0])
        assertEquals("r", pattern.words[1])
        assertTrue { pattern.endsWithSpace }
        assertTrue { pattern.ignoreCase }
    }

    @Test
    fun `parsePattern endWithSpace`() {
        val pattern = parsePattern("FooBar ")
        assertEquals("Foo", pattern.words[0])
        assertEquals("Bar", pattern.words[1])
        assertTrue { pattern.endsWithSpace }
        assertFalse { pattern.ignoreCase }
    }

    @Test
    fun `remove extra wildcards`() {

        val pattern = parsePattern("F*********oB***********r******* ")
        assertEquals("F*o", pattern.words[0])
        assertEquals("B*r*", pattern.words[1])
        assertTrue { pattern.endsWithSpace }
        assertFalse { pattern.ignoreCase }
    }

    @Test
    fun `M*r `() {

        val pattern = parsePattern("M*r ")
        assertEquals("M*r", pattern.words[0])
//        assertEquals("B*r*", pattern.words[1])
        assertTrue { pattern.endsWithSpace }
        assertFalse { pattern.ignoreCase }
    }
}