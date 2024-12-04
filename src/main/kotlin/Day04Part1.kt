import structure.LinesPuzzle
import utils.Vector3D

class Day04Part1 : LinesPuzzle() {
    override fun solve(lines: List<String>): String {
        val height = lines.size
        val width = lines[0].length

        val searchTerm = "XMAS"
        var count = 0

        for(i in 0 until height ) {
            for(j in 0 until width) {
                val pos = Vector3D(i,j,0)
                for(dir in Vector3D.Directions2dWithDiagonals) {

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
                        count++
                    }
                }

            }
        }

        return count.toString()
    }

    companion object {

    }
}