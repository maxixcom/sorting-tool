package sorting.console.io

class PrinterStdout : Printer {
    override fun println(str: String?) {
        kotlin.io.println(str)
    }
}
