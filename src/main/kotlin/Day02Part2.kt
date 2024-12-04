import structure.LinesPuzzle

class Day02Part2 : LinesPuzzle() {

    override fun solve(lines: List<String>): String {
        val parsedLines = lines.map { it.split(" ").map { it.toLong() } }
        val lines = parsedLines.map { Line(it) }

        val results = lines.associateWith { it.isSave2() }
        return results.values.count { it }.toString()
    }

    class Line(val line: List<Long>) {
        val diff = line.windowed(2, 1).map { it[1] - it[0] }
        fun isSave2(): Boolean {

            val goingUp = diff.count { it >= 1 } > (line.size / 2)
            val dir = if (goingUp) 1 else -1

            val firstFail = diff.indexOfFirst { (it * dir > 3 || it * dir < 1) }
            if (firstFail == -1) {
                return isSave1()
            } else {
                val sublist1 = line.subList(0, firstFail) + line.subList(firstFail + 1, line.size)
                val sublist2 = line.subList(0, firstFail + 1) + line.subList(firstFail + 2, line.size)
                return Line(sublist1).isSave1() ||
                        Line(sublist2).isSave1()
            }

        }

        fun isSave1(): Boolean {
            return diff.all { it >= 1 && it <= 3 } || diff.all { it <= -1 && it >= -3 }
        }
    }

    companion object {
    }
}