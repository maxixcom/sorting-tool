package sorting

import java.util.Scanner

fun main() {
    val s = Scanner(System.`in`)
    val input = mutableListOf<String>()
    while (s.hasNext()) {
        input.add(s.nextLine())
    }

    val regex = "-?\\d+".toRegex()
    val numbers = input
        .flatMap {
            regex.findAll(it)
                .map { m ->
                    m.groups[0]!!.value.toInt()
                }
                .toList()
        }

    val index = numbers
        .groupingBy { it }
        .fold(0) { acc, _ -> acc + 1 }

    println("Total numbers: ${numbers.size}.")
    val max = index.keys.maxOrNull()
    max?.let {
        println("The greatest number: $it (${index[max]} time(s)).")
    }
}
