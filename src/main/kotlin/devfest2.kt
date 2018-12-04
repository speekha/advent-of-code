import java.util.*

/*
 * Complete the timeConversion function below.
 */
fun timeConversion(s: String): String {
    /*
     * Write your code here.
     */
    var (hours, minutes, seconds) = s.dropLast(2).split(":").map { it.toInt() }
    if (hours == 12) {
        if (s.takeLast(2) == "AM") {
            hours -= 12
        }
    } else {
        if (s.takeLast(2) == "PM") {
            hours += 12
        }
    }

    return "%02d:%02d:%02d".format(hours, minutes, seconds)
}

fun main() {
    val scan = Scanner(System.`in`)

//    val s = scan.nextLine()

    for (j in listOf("AM", "PM"))
        for (i in 1..12) {

            val result = timeConversion("$i:00:00$j")

            println(result)
        }

}