package functions


abstract class Progression
{
    companion object
    {
        fun arithmetic(start: Int, step: Int, n: Int): List<Int>
        {
            return List(n) { index ->
                start + step * index
            }
        }

        fun geometric(start: Int, step: Int, n: Int): List<Int>
        {
            return List(n) { index ->
                start * pow(step, index)
            }
        }
    }
}