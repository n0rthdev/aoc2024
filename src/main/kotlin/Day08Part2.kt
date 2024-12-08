import structure.LinesPuzzle
import utils.Vector3D

class Day08Part2 : LinesPuzzle() {
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

                    var cur = it.value[b1]
                    while(cur.xInRangeIncluding(0, width.toLong() - 1) && cur.yInRangeIncluding(
                            0,
                            height.toLong() - 1
                        ))
                    {
                        antinodes.add(cur)
                        cur += diff
                    }

                    cur = it.value[b1]
                    while(cur.xInRangeIncluding(0, width.toLong() - 1) && cur.yInRangeIncluding(
                            0,
                            height.toLong() - 1
                        ))
                    {
                        antinodes.add(cur)
                        cur -= diff
                    }
                }
            }
        }

        return antinodes.size.toString()
    }

    companion object {

    }
}