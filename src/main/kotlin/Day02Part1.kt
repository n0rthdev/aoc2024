import structure.LinesPuzzle

class Day02Part1 : LinesPuzzle() {
    override fun solve(lines: List<String>): String {
        val parsedLines = lines.map { it.split(" ").map { it.toLong() }  }
        val diffs = parsedLines.map { it.windowed(2,1).map { it[1] - it[0] } }

        return diffs.count { it.all { it >= 1 && it <= 3 } || it.all { it <= -1 && it >= -3 }  }.toString()
    }

    companion object {

    }
}