package ru.pletneva

import java.io.IOException

class Main {

    fun main(args: Array<String>) = try {
        val checkedArgs = checkArgs(args)
        val classes = readClasses(checkedArgs[0])
        val classNames: List<String> = ClassFinder(classes).findMatchedClasses(checkedArgs[1])
        classNames.forEach(System.out::println)
    } catch (e: IllegalArgumentException) {
        println(e.message)
    } catch (e: IOException) {
        println(e.message)
    }

    private fun checkArgs(args: Array<String>): Array<String> {
        return when (args.size) {
            0 -> throw IllegalArgumentException("missing filename and pattern")
            1 -> throw IllegalArgumentException("missing pattern")
            2 -> args
            else -> throw IllegalArgumentException("too many arguments")
        }
    }
}