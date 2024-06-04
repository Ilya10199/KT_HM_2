package ru.netology

import kotlin.math.max

fun main() {
    agoToText("ilya", 182810)
    println(calculationCommission("Mastercard", 0, 150_000))
}

fun agoToText(name: String, time: Int) {
    when (time) {
        in 0..60 -> println("$name «был(а) только что»")
        in 61..3_600 -> println("$name «был(а) " + forMin(time) + " назад")
        in 3_601..86_400 -> println("$name «был(а) " + forHour(time) + " назад")
        in 86_401..172_800 -> println("$name «был(а) вчера»")
        in 172_801..259_200 -> println("$name «был(а) позавчера»")
        else -> println("$name «был(а) давно»")
    }
}

fun forMin(time: Int): String {
    return when (val minute: Int = time / 60) {
        1, 21, 31, 41, 51 -> "$minute минуту"
        2, 22, 32, 42, 52 -> "$minute минуты"
        5, 25, 35, 45, 55 -> "$minute минут"
        else -> "$minute минут"

    }
}

fun forHour(time: Int): String {
    return when (val hour: Int = time / 3_600) {
        1, 21 -> "$hour час"
        2, 3, 4, 22, 23, 24 -> "$hour часа"
        else -> "$hour часов"
    }
}

fun calculationCommission(card: String = "Mir", previousAmount: Int = 0, amount: Int): Int {

    val errorCard = -1
    val errorLimit = -2
    val monthlyLimit = 600_000
    val monthlyLimitMasterCard = 75_000
    val dayLimit = 150_000

    if (dayLimit < amount || monthlyLimit < amount + previousAmount) return errorLimit

    return when (card) {
        "Mastercard" -> if (monthlyLimitMasterCard > amount + previousAmount) {
            (((amount + previousAmount) - monthlyLimitMasterCard) * 0.006 + 20).toInt()
        } else 0
        "Visa" -> max(35, (amount * 0.0075).toInt())
        "Mir" -> 0
        else -> errorCard
    }
}
