import functions.Arithmetic
import functions.Progression
import functions.Geometric


fun main()
{
    val re = listOf(
        Regex("""^(?<operation>история) (?<arg1>арифметические|прогрессионные|геометрические)$"""),
        Regex("""^(?<arg1>(?:\+|\-)?\d+) *(?<operation>\+|\-|\*|\/) *(?<arg2>(?:\+|\-)?\d+)$"""),
        Regex("""^(?<operation>арифметическая|геометрическая) (?<arg1>(?:\+|\-)?\d+) (?<arg2>(?:\+|\-)?\d+) (?<arg3>(?:\+|\-)?\d+)$"""),
        Regex("""^(?<operation>периметр)(?<arg1>(?: \+?\d+){2,})$"""),
        Regex("""^(?<operation>площадь) (?<arg1>\+?\d+)(?: (?<arg2>\+?\d+))?$"""),
        Regex("""^(?<operation>выход)$""")
    )

    val logger = Logger("logs", "arithmetic.log", "progression.log", "geometric.log")

    while (true)
    {
        print("> ")
        val input = readln()

        var regexResult: MatchResult? = null

        for (r in re) {
            regexResult = r.matchEntire(input)
            if (regexResult !== null)
                break
        }

        if (regexResult === null) {
            println("Недопустимое выражение")
            continue
        }

        val operation = regexResult.groups["operation"]?.value
        val arg1 = try { regexResult.groups["arg1"]?.value } catch (_: Exception) { null }
        val arg2 = try { regexResult.groups["arg2"]?.value } catch (_: Exception) { null }
        val arg3 = try { regexResult.groups["arg3"]?.value } catch (_: Exception) { null }

        when
        {
            operation == "выход" -> break

            operation == "история" && arg1 !== null -> {
                when (arg1) {
                    "арифметические" -> println(logger.get(LogType.ARITHMETIC).joinToString("\n"))
                    "прогрессионные" -> println(logger.get(LogType.PROGRESSION).joinToString("\n"))
                    "геометрические" -> println(logger.get(LogType.GEOMETRIC).joinToString("\n"))
                }
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

            operation == "арифметическая" && arg1 !== null && arg2 !== null && arg3 !== null -> {
                val start = arg1.toInt()
                val step = arg2.toInt()
                val n = arg3.toInt()
                val result = Progression.arithmetic(start, step, n)
                logger.add(LogType.PROGRESSION, "$operation; $step;\n$result")
            }

            operation == "геометрическая" && arg1 !== null && arg2 !== null && arg3 !== null -> {
                val start = arg1.toInt()
                val step = arg2.toInt()
                val n = arg3.toInt()
                val result = Progression.geometric(start, step, n)
                logger.add(LogType.PROGRESSION, "$operation; $step;\n$result")
            }

            operation == "периметр" && arg1 !== null -> {
                val args = arg1.split(" ").filter { it != "" }.map { it.toInt() }
                val result = Geometric.perimeter(args)
                logger.add(LogType.GEOMETRIC, "$operation $args = $result")
            }

            operation == "площадь" ->
            {
                if (arg1 !== null && arg2 !== null)
                {
                    val a = arg1.toInt()
                    val b = arg2.toInt()
                    val result = Geometric.rectangleSquare(a, b)

                    logger.add(LogType.GEOMETRIC, "$operation [$a, $b] = $result")
                }
                else if (arg1 !== null)
                {
                    val a = arg1.toInt()
                    val result = Geometric.circleSquare(a)

                    logger.add(LogType.GEOMETRIC, "$operation [$a] = $result")
                }
            }
        }
    }
}
