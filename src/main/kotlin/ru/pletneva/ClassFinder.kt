package ru.pletneva


class ClassFinder(private var classNames: List<String>) {

    private val DELIMITER: Char = '.'

    fun findMatchedClasses(pattern: String): List<String> {
        return sortClasses(classNames.filter { name -> match(name, pattern) } as MutableList<String>)
    }

    fun sortClasses(classes: MutableList<String>): MutableList<String> {
        classes.sortBy { it.substringAfterLast(DELIMITER) }
        return classes
    }

    private fun match(fullName: String, patternStr: String): Boolean {
        val name: String = fullName.substringAfterLast(DELIMITER)
        val pattern: Pattern = parsePattern(patternStr)
        var classParts = mutableListOf<String>()
        if (!pattern.ignoreCase) {
            classParts = splitByUpperCase(name).toMutableList()
        } else {
            name.forEach { c -> classParts.add(c.toString()) }
        }
        return matchParts(pattern, classParts, indPattern = 0, indClass = 0)
    }

    private fun matchParts(
        pattern: Pattern,
        classes: List<String>,
        indPattern: Int,
        indClass: Int
    ): Boolean {

        var indexClass = indClass
        var indexPattern = indPattern
        if (pattern.words.size == indexPattern) {
            if (pattern.endsWithSpace && classes.size > indexClass) return false
            return true
        }
        if (classes.size == indexClass) return false
        val firstPattern = pattern.words[indexPattern]
        val firstClass = classes[indexClass]

        if (firstPattern.contains(WILDCARD)) {
            val patternBlocks = firstPattern.split(WILDCARD)
            var classWordPosition = 0

            for (block in patternBlocks) {
                var result = -1
                while (result == -1 && indexClass < classes.size) {
                    result = classes[indexClass].indexOf(block, classWordPosition)
                    if (result == -1) {
                        indexClass += 1
                        classWordPosition = 0
                    }
                }
                if (indexClass == classes.size) return false
                classWordPosition += result + 1
            }
            indexPattern += 1
        }
        return if (compareParts(firstPattern, firstClass, pattern.ignoreCase)) {
            matchParts(pattern, classes, indexPattern + 1, indexClass + 1)
        } else {
            matchParts(pattern, classes, indexPattern, indexClass + 1)
        }
    }

    private fun compareParts(pattern: String, className: String, ignoreCase: Boolean) =
        className.contains(pattern, ignoreCase)
}