import structure.LinesPuzzle
import utils.Vector3D

class Day08Part1 : LinesPuzzle() {
    override fun solve(lines: List<String>): String {
        val height = lines.size
        val width = lines[0].length

        val map = mutableMapOf<Vector3D, Char>()

        for ((i, line) in lines.withIndex()) {
            for ((j, ch) in line.withIndex()) {
                if (ch != '.') {
                    map[Vector3D(i, j, 0)] = ch
                }
            }
        }

        val mapByChar = map.entries.groupBy({ it.value }, { it.key })

        val antinodes = mutableSetOf<Vector3D>()

        mapByChar.map {
            for (b1 in 0..<it.value.size) {
                for (b2 in (b1 + 1)..<it.value.size) {
                    val diff = it.value[b2] - it.value[b1]

                    val p1 = it.value[b1] - diff
                    val p2 = it.value[b2] + diff
                    antinodes.add(p1)
                    antinodes.add(p2)
                }
            }
        }

        val inGrid = antinodes.filter {
            it.xInRangeIncluding(0, width.toLong() - 1) && it.yInRangeIncluding(
                0,
                height.toLong() - 1
            )
        }

        return inGrid.size.toString()
    }

    companion object {

    }
}