package functions


abstract class Geometric
{
    companion object
    {
        fun perimeter(sides: List<Int>) = if (sides.size == 2) 2 * (sides[0] + sides[1]) else sides.sum()

        fun rectangleSquare(a: Int, b: Int) = a.toDouble() * b
        fun circleSquare(l: Int) = pow(l, 2) / (4 * Math.PI)
    }
}
