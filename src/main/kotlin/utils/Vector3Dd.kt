package utils

data class Vector3Dd(val x : Double, val y : Double, val z : Double) {

    operator fun unaryMinus(): Vector3Dd {
        return Vector3Dd(-x, -y, -z)
    }

    operator fun unaryPlus(): Vector3Dd {
        return this
    }

    operator fun plus(other: Vector3Dd): Vector3Dd {
        return Vector3Dd(x + other.x, y + other.y, z + other.z)
    }

    operator fun minus(other: Vector3Dd): Vector3Dd {
        return Vector3Dd(x - other.x, y - other.y, z - other.z)
    }

    operator fun times(other: Vector3Dd): Vector3Dd {
        return Vector3Dd(x * other.x, y * other.y, z * other.z)
    }

    operator fun times(times: Double): Vector3Dd {
        return Vector3Dd(x * times, y * times, z * times)
    }

    operator fun times(times: Long): Vector3Dd {
        return times(times.toDouble())
    }

    operator fun times(times: Int): Vector3Dd {
        return times(times.toDouble())
    }

    operator fun div(other: Vector3Dd): Vector3Dd {
        return Vector3Dd(x / other.x, y / other.y, z / other.z)
    }

    operator fun div(div: Double): Vector3Dd {
        return Vector3Dd(x / div, y / div, z / div)
    }

    operator fun div(div: Long): Vector3Dd {
        return div(div.toDouble())
    }

    operator fun div(div: Int): Vector3Dd {
        return div(div.toDouble())
    }

    fun cross(other: Vector3Dd) = Vector3Dd(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x)

    fun dot(other: Vector3Dd) = x * other.x + y * other.y + z * other.z


    fun inCuboid(corner1: Vector3Dd, corner2: Vector3Dd): Boolean {
        return xInRangeIncluding(corner1.x, corner2.x) &&
                yInRangeIncluding(corner1.y, corner2.y) &&
                zInRangeIncluding(corner1.z, corner2.z)
    }


    fun xInRangeIncluding(x1: Double, x2: Double): Boolean {
        val min = Math.min(x1, x2)
        val max = Math.max(x1, x2)

        return min <= x && x <= max
    }

    fun yInRangeIncluding(y1: Double, y2: Double): Boolean {
        val min = Math.min(y1, y2)
        val max = Math.max(y1, y2)

        return min <= y && y <= max
    }

    fun zInRangeIncluding(z1: Double, z2: Double): Boolean {
        val min = Math.min(z1, z2)
        val max = Math.max(z1, z2)

        return min <= z && z <= max
    }

    companion object{
        fun fromString(str: String, separator: String = ",", dim: Int = 3): Vector3Dd {
            require(dim in 1..3) { "dim must be between 0 and 3" }
            try {
                val parts = str.split(separator).map { it.trim().toDouble() }
                check(parts.size == dim)
                return Vector3Dd(parts.getOrNull(0) ?: 0.0, parts.getOrNull(1) ?: 0.0, parts.getOrNull(2) ?: 0.0)
            } catch (ex: Exception) {
                throw IllegalArgumentException("Could not parse $str with $separator to Vector3Dd($dim)", ex)
            }
        }
    }
}