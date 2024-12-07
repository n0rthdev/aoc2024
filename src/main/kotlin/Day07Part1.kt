import structure.LinesPuzzle

class Day07Part1 : LinesPuzzle() {
    override fun solve(lines: List<String>): String {
        return lines.sumOf {
            val parts = it.split(":")
            val result = parts[0].toLong()
            val nums = parts[1].trim().split(" ").map { it.toLong() }
            val sol = findSolution(result, nums, 0, 0)
            if (sol) {
                result
            } else {
                0
            }
        }.toString()
    }

    fun findSolution(target: Long, numbers: List<Long>, idx: Int, sum: Long): Boolean {
        if (idx == numbers.size) {
            return target == sum
        } else if (target < sum) {
            return false
        } else {
            return findSolution(target, numbers, idx + 1, sum + numbers[idx])
                    || findSolution(target, numbers, idx + 1, sum * numbers[idx])
        }
    }

    companion object {

    }
}