import structure.LinesPuzzle
import utils.Vector3D

class Day04Part2 : LinesPuzzle() {
    override fun solve(lines: List<String>): String {
        val height = lines.size
        val width = lines[0].length

        val searchTerm = "MAS"
        val startingPoints = mutableListOf<Xmas>()

        for(i in 0 until height ) {
            for(j in 0 until width) {
                val pos = Vector3D(i,j,0)
                for(dir in Vector3D.Diagonals2d) {
                    var currentpos = pos
                    var wrongLetter = false
                    for (l in searchTerm) {
                        if(currentpos.x < 0 || currentpos.y < 0 || currentpos.x >= height || currentpos.y >= width || lines[currentpos.x.toInt()][currentpos.y.toInt()] != l) {
                            wrongLetter = true
                            break
                        }
                        currentpos += dir
                    }
                    if (!wrongLetter){
                        startingPoints.add(Xmas(pos, dir))
                    }
                }
            }
        }

        val count = startingPoints.count { cur -> startingPoints.any { (cur.pos + cur.dir == it.pos + it.dir) && cur.dir.dot(it.dir) == 0L} }

        return (count/2).toString()
    }

    data class Xmas(val pos: Vector3D, val dir: Vector3D)

    companion object {

    }
}