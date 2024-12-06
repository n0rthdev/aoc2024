import structure.LinesPuzzle
import utils.Vector3D

class Day06Part2 : LinesPuzzle() {
    override fun solve(lines: List<String>): String {
        val map = mutableMapOf<Vector3D, Boolean>()
        var start = Vector3D(0, 0, 0)
        for ((i, l) in lines.withIndex()) {
            for ((j, c) in l.withIndex()) {
                val pos = Vector3D(j,i, 0)
                if (c == '^') {
                    start = pos
                    map[pos] = true
                } else if (c == '#') {
                    map[pos] = false
                } else {
                    map[pos] = true
                }
            }
        }

        var visited = mutableListOf<Vector3D>(start)
        var currPos = start
        var currentDir = Vector3D.SOUTH

        while(currPos.min() >= 0 && currPos.y < lines.size && currPos.x < lines[0].length) {
            val nextPos = currPos + currentDir
            if (map[nextPos] == null) {
                break;
            }
            else if (map[nextPos] == true) {
                visited.add(nextPos)
                currPos = nextPos
            }
            else {
                currentDir = currentDir.turnLeft2d()
            }
        }


        val possibleBlocks = visited.toSet()

        return possibleBlocks.count { block ->

            var visited = mutableListOf<Pair<Vector3D,Vector3D>>(start to Vector3D.SOUTH)
            var currPos = start
            var currentDir = Vector3D.SOUTH
            var loop = false

            while(currPos.min() >= 0 && currPos.y < lines.size && currPos.x < lines[0].length) {
                val nextPos = currPos + currentDir
                if (map[nextPos] == null) {
                    break;
                }
                else if (map[nextPos] == true && nextPos != block) {
                    if(visited.contains(nextPos to currentDir))
                    {
                        loop = true
                        break
                    }
                    visited.add(nextPos to currentDir)
                    currPos = nextPos
                }
                else {
                    currentDir = currentDir.turnLeft2d()
                }
            }
            loop
        }.toString()
    }

    companion object {

    }
}