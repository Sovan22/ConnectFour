fun main() {
    var n = readln().toInt()
    val list = mutableListOf<Int>()
    while(n>0)
    {
        list.add(readln().toInt())
        n--
    }
    val m = readln().toInt()
    if(m in list)
        print("YES")
    else
        print("NO")

}