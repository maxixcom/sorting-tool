fun main() {
    val (a, b, c) = List(3) { readLine()!!.toDouble() }
    println((c - b) / a)
}
