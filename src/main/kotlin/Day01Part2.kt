import structure.LinesPuzzle
import kotlin.math.absoluteValue

class Day01Part2 : LinesPuzzle() {


    companion object {

    }

    override fun solve(lines: List<String>): String {
        val firstList = mutableListOf<Long>()
        val secondList = mutableListOf<Long>()
        lines.forEach {
            val splitted = it.split(" ").filterNot { it.isBlank() }
            firstList.add(splitted[0].toLong())
            secondList.add(splitted[1].toLong())
        }

        val counts = secondList.groupingBy { it }.eachCount()
        return firstList.sumOf { it * counts.getOrDefault(it,0) }.toString()
    }
}