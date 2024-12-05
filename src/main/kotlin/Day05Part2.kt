import structure.LinesPuzzle

class Day05Part2 : LinesPuzzle() {
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

        val invalidPrints = mutableListOf<List<Long>>()

        for (p in prints) {
            var valid = true
            for (i in 0..<p.size) {
                for (j in i..<p.size) {
                    if(ruleBook[p[j]]?.contains(p[i]) == true) {
                        valid = false
                    }
                }
            }
            if(!valid) {
                invalidPrints.add(p)
            }
        }

        val newprints = invalidPrints.map { print ->
            val relevantRules = rules.filter { print.contains(it.before) && print.contains(it.after) }

            val newPrint = mutableListOf<Long>()
            var rulesCur = relevantRules
            var printCur = print
            while (print.size != newPrint.size) {
                val toAdd = printCur - rulesCur.map { it.after }.toSet()
                newPrint.addAll(toAdd)
                rulesCur = rulesCur.filter { it.before !in toAdd }
                printCur = printCur.filter { it !in toAdd }
            }
            newPrint
        }

        return newprints.sumOf { it[it.size/2] }.toString()
    }

    data class Rule(val before: Long, val after: Long)

    companion object {

    }
}