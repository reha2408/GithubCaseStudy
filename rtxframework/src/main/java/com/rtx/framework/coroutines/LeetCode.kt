package com.rtx.framework.coroutines

fun main() {
    print(isPalindrome(121555121))
    print(intToRoman(1991))
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

fun intToRoman(num: Int): String {
    var str = ""
    var number = num
    val pairs = listOf(
        (1000 to "M"),
        (900 to "CM"),
        (500 to "D"),
        (400 to "CD"),
        (100 to "C"),
        (90 to "XC"),
        (50 to "L"),
        (40 to "XL"),
        (10 to "X"),
        (9 to "IX"),
        (5 to "V"),
        (4 to "IV"),
        (1 to "I")
    )
    while (number > 0) {
        pairs.forEach {
            while (number - it.first >= 0) {
                number -= it.first
                str += it.second
            }
        }
    }
    return str
}