package sorting.console.service

interface SortService {
    fun sortIntegers(lines: List<String>): Result<List<Int>>
}
