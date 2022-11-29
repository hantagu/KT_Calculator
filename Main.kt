import functions.Arithmetic
import functions.Progression
import functions.Geometric


fun main()
{
    val re = listOf(
        Regex("""^(?<operation>история) (?<arg1>арифметические|прогрессионные|геометрические)$"""),
        Regex("""^(?<arg1>((?:\+|\-)?\d+)) ?(?<operation>\+|\-|\*|\/) ?(?<arg2>((?:\+|\-)?\d+))$"""),
        Regex("""^(?<operation>арифметическая|геометрическая) (?<arg1>((?:\+|\-)?\d+)) (?<arg2>((?:\+|\-)?\d+)) (?<arg3>((?:\+|\-)?\d+))$"""),
        Regex("""^(?<operation>периметр|площадь) (?<arg1>(?:((?:\+|\-)?\d+) ?)+)$""")
    )

    val logger = Logger("logs", "arithmetic.log", "progression.log", "geometric.log")

    print("> ")
    val input = readln()

    var regexResult: MatchResult? = null
    for (i in re)
    {
        regexResult = i.matchEntire(input)

        if (regexResult !== null)
            break
    }

    if (regexResult === null)
    {
        println("Недопустимое выражение")
        return
    }

    val operation = try { regexResult.groups["operation"]?.value } catch (_: Exception) { null }
    val arg1 = try { regexResult.groups["arg1"]?.value } catch (_: Exception) { null }
    val arg2 = try { regexResult.groups["arg2"]?.value } catch (_: Exception) { null }
    val arg3 = try { regexResult.groups["arg3"]?.value } catch (_: Exception) { null }

    when
    {
        operation == "история" && arg1 !== null -> {
            println(when (arg1) {
                "арифметические" -> logger.get(LogType.ARITHMETIC)
                "прогрессионные" -> logger.get(LogType.PROGRESSION)
                "геометрические" -> logger.get(LogType.GEOMETRIC)
                else -> null
            }?.joinToString("\n"))
        }

        operation == "+" && arg1 !== null && arg2 !== null -> {
            val a = arg1.toInt()
            val b = arg2.toInt()
            val result = Arithmetic.add(a, b)
            logger.add(LogType.ARITHMETIC, "$a $operation $b = $result")
        }

        operation == "-" && arg1 !== null && arg2 !== null -> {
            val a = arg1.toInt()
            val b = arg2.toInt()
            val result = Arithmetic.subtract(a, b)
            logger.add(LogType.ARITHMETIC, "$a $operation $b = $result")
        }

        operation == "*" && arg1 !== null && arg2 !== null -> {
            val a = arg1.toInt()
            val b = arg2.toInt()
            val result = Arithmetic.multiply(a, b)
            logger.add(LogType.ARITHMETIC, "$a $operation $b = $result")
        }

        operation == "/" && arg1 !== null && arg2 !== null -> {
            val a = arg1.toInt()
            val b = arg2.toInt()
            val result = Arithmetic.divide(a, b)
            logger.add(LogType.ARITHMETIC, "$a $operation $b = $result")
        }

        operation == "арифметическая" && arg1 !== null && arg2 !== null -> {
            val start = arg1.toInt()
            val step = arg2.toInt()
            val n = arg3!!.toInt()
            val result = Progression.arithmetic(start, step, n)
            logger.add(LogType.PROGRESSION, "$operation; $step;\n$result")
        }

        operation == "геометрическая" && arg1 !== null && arg2 !== null -> {
            val start = arg1.toInt()
            val step = arg2.toInt()
            val n = arg3!!.toInt()
            val result = Progression.geometric(start, step, n)
            logger.add(LogType.PROGRESSION, "$operation; $step;\n$result")
        }

        operation == "периметр" && arg1 !== null -> {
            val args = arg1.split(" ").map { it.toInt() }
            val result = Geometric.perimeter(args)
            logger.add(LogType.GEOMETRIC, "$operation $args = $result")
        }

        operation == "площадь" && arg1 !== null -> {
            val args = arg1.split(" ").map { it.toInt() }
            val result = if (args.size == 1) Geometric.circleSquare(args[0]) else Geometric.rectangleSquare(args[0], args[1])
            logger.add(LogType.GEOMETRIC, "$operation $args = $result")
        }
    }
}