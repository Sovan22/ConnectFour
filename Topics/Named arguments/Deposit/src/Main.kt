import kotlin.math.pow

fun calcCi(amount : Double = 1000.0, percent : Double = 5.0, years : Int = 10) : Int
{
    val res : Double = amount*(1+percent/100).pow(years)
    return res.toInt()
}
//amount, percent, or years
fun main() {
    val choice = readln()
    when (choice)
    {
        "amount" -> println(calcCi(amount = readln().toDouble()))
        "percent" -> println(calcCi(percent = readln().toDouble()))
        "years" -> println(calcCi(years = readln().toInt()))
        else -> println("Wrong Choice")
    }
}