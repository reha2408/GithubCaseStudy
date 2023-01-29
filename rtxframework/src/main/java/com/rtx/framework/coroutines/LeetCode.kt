package com.rtx.framework.coroutines

fun main() {
    println(isPalindrome(121555121))

    val org = 1994
    val roman = intToRoman(org)
    val int = romanToInt(roman)

    println(org)
    println(roman)
    println(int)

    val t = "cab" // "aabccccd"
    val p = "c*a*b" // "a*bc*d"
    println(isMatch(t,p))
}

fun isPalindrome(x: Int): Boolean {
    if (x < 0) { return false }
    else {
        val str = x.toString()
        for(i in 0..str.length/2) {
            if (str[i] != str[str.length-1 - i])
                return false
        }
        return true
    }
}

fun romanToInt(roman: String): Int {
    var result = 0
    var strRoman = roman

    val romans = listOf(
        (1000 to "M"),
        (500 to "D"),
        (100 to "C"),
        (50 to "L"),
        (10 to "X"),
        (5 to "V"),
        (1 to "I")
    )
    val specials = listOf(
        (900 to "CM"),
        (400 to "CD"),
        (90 to "XC"),
        (40 to "XL"),
        (9 to "IX"),
        (4 to "IV")
    )

    specials.forEach { romanPair ->
        strRoman.windowed(romanPair.second.length) {
            if (it == romanPair.second)
                result += romanPair.first
        }
        strRoman = strRoman.replace(romanPair.second, "")
    }
    romans.forEach { romanPair ->
        strRoman.windowed(romanPair.second.length) {
            if (it == romanPair.second)
                result += romanPair.first
        }
        strRoman = strRoman.replace(romanPair.second, "")
    }

    return result
}

fun intToRoman(num: Int): String {
    val numbs = listOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4 , 1)
    val romans = listOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")

    var str = ""
    var number = num
    while (number > 0) {
        numbs.forEachIndexed { index, i ->
            while (number - i >= 0) {
                number -= i
                str += romans[index]
            }
        }
    }
    return str
}

/**
 * "aa", p = "a" -> false
 *
 * "aa", p = "a*" -> true
 *
 * "ab", p = ".*" -> true
 *
 */
fun isMatch(s: String, p: String): Boolean {
    var t = s
    var x = p
    val astIndices = mutableListOf<Int>()
    x.forEachIndexed { index, c ->
        if (c == '*')
            astIndices.add(index)
    }
    astIndices.forEach {
        x = x.replaceFirst('*', x[it - 1])
    }

    if (astIndices.isEmpty()) {
        return t == x
    }
    if (x.all { it == '.' }) {
        return true
    }

    var startIndex = 0
    var tokens = mutableListOf<Pair<String, Boolean>>()
    astIndices.forEachIndexed { index, sIndex ->
        tokens.add(x.substring(startIndex, sIndex) to false)
        tokens.add(x.substring(sIndex, sIndex + 1) to true)
        startIndex = sIndex + 1

        if (astIndices.lastIndex == index) {
            tokens.add(x.substring(startIndex, x.length) to false)
        }
    }

    var grandCheck = true
    tokens.forEach {
        if (it.second) {
            val diffIndex = t.indexOfFirst { char ->
                it.first.first() != char
            }

            var check = true
            if (diffIndex != -1) {
                t.substring(0, diffIndex).forEach { ch ->
                    if (ch != it.first.first() && ch != '.') {
                        check = false
                    }
                }
                t = t.substring(diffIndex)
            }
            grandCheck = check
        } else {
            grandCheck = it.first == t.substring(0, it.first.length)
            t = t.substring(it.first.length)
        }
        if (!grandCheck)
            return false
    }
    return grandCheck
}