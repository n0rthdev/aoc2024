package utils

data class Line3Dd (val direction : Vector3Dd, val point : Vector3Dd) {
    companion object {
        fun intersect(line1: Line3Dd, line2: Line3Dd): Vector3Dd? {
            val d1 = line1.direction
            val d2 = line2.direction
            val p1 = line1.point
            val p2 = line2.point

            val p21 = p2 - p1
            val d1xd2 = d1.cross(d2)

            // Check if lines are parallel or skew,
            if (d1xd2 == Vector3Dd(0.0,0.0,0.0)) return null

            val t1 = (p21.cross(d2)).dot(d1xd2) / (d1xd2.dot(d1xd2))

            if(t1 < 0){
                return null
            }

            val c = p1 + d1 * t1

            val t2 = (c - p2) / d2

            if (t2.x < 0){
                return null
            }

            return p1 + d1 * t1
        }
    }
}