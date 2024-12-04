import structure.LinesPuzzle

class Day03Part2 : LinesPuzzle() {
    override fun solve(lines: List<String>): String {
        val input = lines.joinToString(" ")
        val matches = "(do\\(\\)|don't\\(\\)|mul\\([0-9]{1,3},[0-9]{1,3}\\))".toRegex().findAll(input)

        var enabled = true
        var sum = 0L
        for (match in matches) {
            val value = match.value
            if(value.startsWith("do"))
            {
                enabled = !value.endsWith("t()")
            }
            else if(enabled) {
                val digits = value.substring(4, value.length -1 ).split(",").map { it.toLong() }
                sum += digits[0] * digits[1]
            }
        }
        return sum.toString()
    }

    companion object {

    }
}