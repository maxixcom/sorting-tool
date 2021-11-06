package sorting.console.io

import java.io.File

class PrinterFile(
    private val file: File
) : Printer {

    override fun println(str: String?) {
        str?.let {
            file.appendText(it)
        }
    }
}
