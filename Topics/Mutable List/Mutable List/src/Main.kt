fun names(namesList: List<String>): List<String> {
    var count = 1
    val temp = namesList.toMutableList()
    val nameCount = mutableListOf<String>()
    for( i in namesList.indices)
    {
        for(j in i+1 until namesList.size){
            if (temp[i] == "")
                break
            if(temp[i] == temp[j])
            {
                count++
                temp[j] = ""
            }
        }
        if (temp[i] == "")
            continue
        val a = temp[i]
        nameCount.add("The name $a is repeated $count times")
        count = 1
    }
    return nameCount
}

//fun main()
//{
//    val namesList = listOf("Vasya", "Olga", "Vasya", "Dima", "Masha", "Vasa", "Olga")
//    val nc = names(namesList)
//    println(nc)
//}
