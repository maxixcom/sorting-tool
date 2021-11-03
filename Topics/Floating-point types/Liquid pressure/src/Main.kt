const val GRAVITY = 9.8

fun main() {
    val density = readLine()!!.toDouble()
    val height = readLine()!!.toDouble()
    println(density * GRAVITY * height)
}
