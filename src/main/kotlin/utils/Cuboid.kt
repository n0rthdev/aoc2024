package utils

data class Cuboid(val start : Vector3D, val size :Vector3D) {
    val end = start + size - Vector3D.ONE

    fun intersection(other: Cuboid): Cuboid? {
        val v1 = start.max(other.start)
        val v2 = end.min(other.end)

        if (v1.x <= v2.x && v1.y <= v2.y && v1.z <= v2.z) {
            return Cuboid(v1, v2 - v1 + Vector3D.ONE)
        }

        return null
    }

    fun move(dir : Vector3D): Cuboid {
        return Cuboid(start.move(dir), size)
    }

    fun min() = start.min(end)
    fun max() = start.max(end)
}