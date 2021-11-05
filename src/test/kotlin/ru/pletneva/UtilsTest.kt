package ru.pletneva

import org.junit.Test
import kotlin.test.assertEquals

class UtilsTest {

    @Test
    fun `read file correctly`() {
        assertEquals(
            listOf(
                "a.b.FooBarBaz",
                "c.d.FooBar",
                "liptsoft.WishMaker",
                "liptsoft.MindReader",
                "TelephoneOperator",
                "ScubaArgentineOperator",
                "YoureLeavingUsHere",
                "YouveComeToThisPoint",
                "YourEyesAreSpinningInTheirSockets"
            ),
            readClasses("Input_sample.txt")
        )
    }

    @Test
    fun splitByUpperCase() {
        assertEquals(
            listOf(
                "Foo",
                "Bar",
                "Baz"
            ),
            splitByUpperCase("FooBarBaz")
        )
        assertEquals(
            listOf(
                "fbz",
            ),
            splitByUpperCase("fbz")
        )
        assertEquals(
            listOf(
                "F*o",
                "B*r*"
            ),
            splitByUpperCase("F*oB*r*")
        )
    }
}