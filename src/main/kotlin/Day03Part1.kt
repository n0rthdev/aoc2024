import structure.LinesPuzzle

class Day03Part1 : LinesPuzzle() {
    override fun solve(lines: List<String>): String {
        val input = lines.joinToString(" ")
        val matches = "mul\\([0-9]{1,3},[0-9]{1,3}\\)".toRegex().findAll(input)

//        for (match in matches) {
//            println(match.value)
//        }

        val numberpairs = matches.map { it.value.substring(4, it.value.length -1 ).split(",").map { it.toLong() } }.toList()

        return numberpairs.sumOf { it[0] * it[1] }.toString()
    }

    companion object {

    }
}