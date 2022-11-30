package functions


fun pow(a: Int, b: Int): Int
{
    var result = 1
    repeat(b) { result *= a }
    return result
}
