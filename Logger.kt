import java.io.File


enum class LogType { ARITHMETIC, PROGRESSION, GEOMETRIC }

class Logger(logDirPath: String, arithmeticLogFile: String, progressionLogFile: String, geometricLogFile: String)
{
    private val arithmeticLog: File
    private val progressionLog: File
    private val geometricLog: File

    init
    {
        val logDir = File(logDirPath)

        if (!logDir.exists())
            logDir.mkdirs()

        arithmeticLog = File("${logDir.path}${File.separator}$arithmeticLogFile")
        if (!arithmeticLog.exists())
            arithmeticLog.createNewFile()

        progressionLog = File("${logDir.path}${File.separator}$progressionLogFile")
        if (!progressionLog.exists())
            progressionLog.createNewFile()

        geometricLog = File("${logDir.path}${File.separator}$geometricLogFile")
        if (!geometricLog.exists())
            geometricLog.createNewFile()
    }

    fun add(lt: LogType, msg: String)
    {
        println(msg)

        when (lt) {
            LogType.ARITHMETIC -> arithmeticLog
            LogType.PROGRESSION -> progressionLog
            LogType.GEOMETRIC -> geometricLog
        }.appendText("$msg\n")
    }

    fun get(lt: LogType): List<String>
    {
        return when (lt) {
            LogType.ARITHMETIC -> arithmeticLog
            LogType.PROGRESSION -> progressionLog
            LogType.GEOMETRIC -> geometricLog
        }.readLines()
    }
}
