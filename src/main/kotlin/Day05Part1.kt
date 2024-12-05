import structure.LinesPuzzle

class Day05Part1 : LinesPuzzle() {
    override fun solve(lines: List<String>): String {
        val rules = mutableListOf<Rule>()


        val prints = mutableListOf<List<Long>>()
        var parsingRules = true
        for (line in lines) {
            if (line.length == 0) {
                parsingRules = false
            } else if (parsingRules) {
                val (a, b) = line.split("|").map { it.toLong() }
                rules.add(Rule(a, b))
            } else {
                prints.add(line.split(",").map { it.toLong() })
            }
        }
        val ruleBook = rules.groupBy { it.before }.mapValues { it.value.map { it.after }.toSet() }

        val validPrints = mutableListOf<List<Long>>()

        for (p in prints) {
            var valid = true
            for (i in 0..<p.size) {
                for (j in i..<p.size) {
                    if(ruleBook[p[j]]?.contains(p[i]) == true) {
                        valid = false
                    }
                }
            }
            if(valid) {
                validPrints.add(p)
            }
        }

        return validPrints.sumOf { it[it.size/2] }.toString()
    }

    data class Rule(val before: Long, val after: Long)

    companion object {

    }
}