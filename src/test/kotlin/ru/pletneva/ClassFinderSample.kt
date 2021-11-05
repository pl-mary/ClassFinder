package ru.pletneva


import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ClassFinderSample {

    private lateinit var classFinder: ClassFinder

    @Before
    fun setUp() {
        classFinder = ClassFinder(readClasses("Input_sample.txt"))
    }

    @Test
    fun `findMatchedClasses in the right order`() {
        assertEquals(
            listOf("liptsoft.MindReader", "liptsoft.WishMaker"),
            classFinder.findMatchedClasses("M")
        )

        assertEquals(
            listOf("liptsoft.WishMaker"),
            classFinder.findMatchedClasses("WM")
        )

        assertEquals(
            listOf(
                "YourEyesAreSpinningInTheirSockets",
                "YoureLeavingUsHere", "YouveComeToThisPoint"
            ),
            classFinder.findMatchedClasses("You")
        )

        assertEquals(
            emptyList(),
            classFinder.findMatchedClasses("MaR")
        )

        assertEquals(
            listOf("ScubaArgentineOperator", "TelephoneOperator"),
            classFinder.findMatchedClasses("Op")
        )

        assertEquals(
            listOf("YourEyesAreSpinningInTheirSockets"),
            classFinder.findMatchedClasses("YAS")
        )

    }

    @Test
    fun `findMatchedClasses insensitively`() {
        assertEquals(
            listOf("ScubaArgentineOperator"),
            classFinder.findMatchedClasses("c*g*t")
        )

        assertEquals(
            listOf("liptsoft.MindReader"),
            classFinder.findMatchedClasses("nd")
        )
    }

    @Test
    fun `findMatchedClasses last word`() {
        assertEquals(
            listOf("liptsoft.MindReader"),
            classFinder.findMatchedClasses("R ")
        )
        assertEquals(
            emptyList(),
            classFinder.findMatchedClasses("YA ")
        )

        assertEquals(
            listOf("ScubaArgentineOperator", "TelephoneOperator"),
            classFinder.findMatchedClasses("Op ")
        )
    }
}