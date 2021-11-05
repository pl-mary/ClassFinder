package ru.pletneva

import java.nio.file.Files
import java.nio.file.Paths

val WILDCARD = '*'

fun readClasses(filename: String): List<String> {
    val linesStream = Files.readAllLines(Paths.get(filename))
    return linesStream.filter { clss -> clss.isNotBlank() && clss.isNotBlank() }
        .map(String::trim)
        .toList()
}

fun splitByUpperCase(className: String): List<String> {
    val classWords = mutableListOf<String>()
    for (char in className.toCharArray()) {
        if (char.isUpperCase() || classWords.size == 0) {
            classWords.add(char.toString())
        } else {
            classWords[classWords.size - 1] += char.toString()
        }
    }
    return classWords
}