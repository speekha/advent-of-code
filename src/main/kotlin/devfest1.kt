// Complete the maximumToys function below.
fun maximumToys(prices: Array<Int>, k: Int): Int {

    var total = 0
    var count = 0
    for (i in prices.sorted()) {
        println(i)
        total += i
        if (total <= k) {
            count++
        }
    }
    return count
}

fun main() {
//    val scan = Scanner(System.`in`)
//
//    val nk = scan.nextLine().split(" ")
    val nk = "4 7".split(" ")

    val n = nk[0].trim().toInt()

    val k = nk[1].trim().toInt()

//    val prices = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
    val prices = "1 2 3 4".split(" ").map { it.trim().toInt() }.toTypedArray()

    val result = maximumToys(prices, k)

    println(result)
}
