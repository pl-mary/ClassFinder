package ru.pletneva

import java.lang.StringBuilder

data class Pattern(
    val words: MutableList<String>,
    val endsWithSpace: Boolean,
    val ignoreCase: Boolean
)

fun parsePattern(pattern: String): Pattern {
    val patternEndsWithSpace = pattern.endsWith(" ")
    val reducePattern = removeExtraWildcards(pattern.trim(), '*')
    var ignoreCase = true
    val patternParts = splitByUpperCase(reducePattern) as MutableList<String>
    if (patternParts.size > 1 || patternParts[0][0].isUpperCase()) ignoreCase = false

    if (ignoreCase){
        patternParts.clear()
        val purePattern = reducePattern.replace(WILDCARD.toString(), "")
        for (c in purePattern) {
            patternParts.add(c.toString())
        }
    }
    return Pattern(patternParts, patternEndsWithSpace, ignoreCase)
}

private fun removeExtraWildcards(patternPart: String, wildcard: Char): String {
    run {
        val strbuilder = StringBuilder(patternPart.length)
        var prevChar = ' '
        for (c: Char in patternPart) {
            if (!(prevChar == wildcard && c == wildcard))
                strbuilder.append(c)
            prevChar = c
        }
        return strbuilder.toString()
    }
}