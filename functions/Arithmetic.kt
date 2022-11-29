package functions


abstract class Arithmetic
{
    companion object
    {
        fun add(a: Int, b: Int) = a + b
        fun subtract(a: Int, b: Int) = a - b

        fun multiply(a: Int, b: Int) = a * b
        fun divide(a: Int, b: Int) = if (b != 0) a / b else null
    }
}