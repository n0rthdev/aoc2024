import structure.LinesPuzzle
import kotlin.math.absoluteValue

class Day01Part1 : LinesPuzzle() {


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
        return firstList.sorted().zip(secondList.sorted()).sumOf {
            (it.first - it.second).absoluteValue
        }.toString()
    }
}