import kotlin.math.pow
fun main() {
    val a = readln().toInt()
    val b = readln().toInt()
    val c = readln().toInt()
    val d = readln().toInt()
    for (i in 0..1000)
    {
        val x: Double = i.toDouble()
        val result = a*x.pow(3) + b*x.pow(2) + c*x + d
        if(result == 0.0)
            println(i)
    }
    
}
