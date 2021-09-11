package com.reha.casestudy.feature.github.presentation.repodetail

import java.lang.NumberFormatException

fun main() {
    longestPalindrome("asdasas")
    val x = 1534236469
    print(reverseInteger2(x))
}

fun reverseInteger2(n: Int): Int {
    var x = n
    var rev = 0
    while (x != 0) {
        val pop = x % 10
        x /= 10
        if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
        if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
        rev = rev * 10 + pop
    }
    return rev
}


fun reverseInteger(x: Int): String {
    if (x/10 == 0) {
        return x.toString()
    } else {
        var result = ""
        var _x = x.takeIf { it < 10 }?.let { -it } ?: x
        while (true) {
            val digit = _x % 10
            _x /= 10
            result += digit.toString()
            if (_x < 10) {
                result += _x.toString()
                break
            }
        }

        result.takeIf { it.first() == '0' }?.let { result = result.substring(1) }
        x.takeIf { it < 0 }?.let { result = "-$result" }
        return result
    }
}

fun longestPalindrome(s: String): String {
    s.forEachIndexed { i, c ->
        val next = i + 1
        if (next != s.length) {
            s[i].toString() + s[next]
        }
    }
    println("${isPalindrome("rehareha")} rehareha")
    println("${isPalindrome("babad")} babad")
    println("${isPalindrome2("babad")} babad")
    println("${isPalindrome2("cbbd")} cbbd")
    println("${isPalindrome2("cbbc")} cbbc")
    return ""
}

fun isPalindrome(s: String): Boolean {
    s.forEachIndexed { index, c -> if (c == s.get(s.length-1 - index)) return true }
    return false
}

fun isPalindrome2(s: String): String {
    var largest = ""
    var appending = ""
    var beforeAppending = ""
    for (i in 0..s.lastIndex) {
        beforeAppending = appending
        appending += s[i]
        if (appending[0] == appending[appending.lastIndex]) {
            largest = appending
        } else if (beforeAppending[beforeAppending.lastIndex] == s[i]) {
            largest = beforeAppending
        }
    }
    return largest
}