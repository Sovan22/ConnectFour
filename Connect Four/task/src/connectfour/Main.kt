package connectfour
import java.util.Scanner
import kotlin.system.exitProcess

class Player(var name:String, var symbol: String, var points: Int = 0)
{
    var n = name
    var sy = symbol
    var p = points

}
fun checkWin(w : Int, name : String): Int {
    if(w == 4) {
        println("Player $name won")
//        println("Game over!")
        return 1
    }
    return 0
}
fun checkDraw(board: MutableList<MutableList<String>>):Boolean
{
    for (i in 0 until board.size)
    {
        for(j in 0 until board[0].size)
        {
            if(board[i][j].contains(" "))
                return false
        }
    }
    return true
}
fun checkWinRow(P:Player,board: MutableList<MutableList<String>>,check:Int,c:Int) : Int
{
    var win = 0
    while (true)
    {
        for(i in c-1 downTo 0)
        {
            if(board[check][i] == " ")
                break
            else if(win == 4)
                break
            else
            {
                if(board[check][i] == P.symbol) win++
                else break
            }
        }
        for(i in c until board[0].size)
        {
            if(board[check][i] == " ")
                break
            else if(win == 4)
                break
            else
            {
                if(board[check][i] == P.symbol) win++
                else break
            }
        }
        if(win == 4)
        {
            println("Player "+ P.name +" won")
//            println("Game over!")
            return 1
        }
        else
            break
    }
    return 0
}
fun checkWinCol(P:Player,board: MutableList<MutableList<String>>,check:Int,c:Int): Int
{
    var win = 0
    while (true)
    {
        for(i in check downTo 0)
        {
            if(board[i][c] == " ")
                break
            else if(win == 4)
                break
            else
                if(board[i][c] == P.symbol) win++
                else break
        }
        for(i in check+1 until board.size)
        {
            if(board[i][c] == " ")
                break
            else if(win == 4)
                break
            else
                if(board[i][c] == P.symbol) win++
                else break
        }
        if(win == 4)
        {
            println("Player "+ P.name +" won")
//            println("Game over!")
            return 1
        }
        else
            break
    }
    return 0
}

fun checkWinDg(P:Player,board: MutableList<MutableList<String>>,check:Int,c:Int): Int
{
    var win = 0
    var j = c
    while(true)
    {
        for(i in check downTo 0)
        {
            if(j == board[0].size)
            {
                j = c
                break
            }
            if(board[i][j] == " ")
                break
            else if(win == 4) {
                break
            }
            else
            {
                if(board[i][j] == P.symbol) win++
                else break
            }
            j++
        }
        j = c-1
        for(i in check+1 until board.size)
        {
            if(j < 0)
                break
            if(board[i][j] == " ")
                break
            else if(win == 4)
            {
                break
            }
            else
            {
                if(board[i][j] == P.symbol) win++
                else break
            }
            j--
        }
        var a = checkWin(win,P.name)
        if(a == 1)
            return 1
        win = 1
        j = c - 1
        for(i in check - 1 downTo 0)
        {
            if(j<0)
                break
            if(board[i][j] == " ")
                break
            else if(win == 4)
                break
            else
            {
                if(board[i][j] == P.symbol) win++
                else break
            }
            j--
        }
        j = c + 1
        for(i in check + 1 until board.size)
        {
            if(j == board[0].size)
            {
                j = c
                break
            }
            if(board[i][j] == " ")
                break
            else if(win == 4)
                break
            else
            {
                if(board[i][j] == P.symbol) win++
                else break
            }
            j++
        }
        if(win == 4)
        {
            println("Player "+P.name+" won")
//            println("Game over!")
            return 1
        }
        else
            break
    }
    return 0
}
fun play(P: Player,r: Int ,board: MutableList<MutableList<String>>):Int
{
    if (checkDraw(board))
    {
        println("It is a draw")
//        println("Game over!")
        return 2
    }
    val max_COLUMN = board[0].size
    val regex = Regex("\\d+")
    var co = ""
    var c = 0
    var check = 0
    do {
        do {
            println(P.name + "'s turn:")
            co = readln()
            if(co == "end")
            {
                println("Game over!")
                exitProcess(0)
            }
            else if(!regex.matches(co))
                println("Incorrect column number")
            else if(co.toInt() !in 1..max_COLUMN)
            {
                println("The column number is out of range (1 - $max_COLUMN)")
            }
            else {
                c = co.toInt()
                break
            }
        }while (true)
        var placed = false
        var i = r
        while (i > 0) {
            if (board[i - 1][c - 1].contains(" ")) {

                board[i - 1][c - 1] = P.symbol
                placed = true
                check = i - 1
                break
            }
            i--
        }

        if (!placed)
            println("Column $c is full")
    }while (!placed)
    var go = 0
    drawBoard(board.size,board[0].size,board)
    if(go == 0)
        go = checkWinRow(P,board,check,c)

    if(go == 0)
        go = checkWinCol(P,board,check,c-1)

    if(go == 0)
        go = checkWinDg(P,board,check,c-1)

    return go
}
// 1 2 3 4 5 6 7 8
//║ ║ ║ ║ ║ ║ ║ ║ ║
//║ ║ ║ ║ ║ ║ ║ ║ ║
//║ ║ ║ ║ ║ ║ ║ ║ ║
//║ ║ ║ ║ ║ ║ ║8║ ║
//║ ║ ║ ║ ║ ║ ║ ║ ║
//║ ║ ║ ║ ║ ║ ║ ║ ║
//╚═╩═╩═╩═╩═╩═╩═╩═╝
//fun p2(name2:String,r: Int ,board: MutableList<MutableList<String>>)
//{}

fun drawBoard(r:Int,c:Int,board2D:MutableList<MutableList<String>>)
{
    var i = 1
    while(i<=c)
    {
        print(" $i")
        i++
    }
    println()
    for(i in 1..r) {

        for(j in 1..c)
        {
            print("║")
            print(board2D[i-1][j-1])
        }
        println("║")
    }
    print("╚═")
    for(i in 1 until c)
    {
        print("╩═")
    }
    println("╝")

}

//╚, ═, ╩, ╝


fun main() {
    println("Connect Four")
    println("First player's name:")
    var p1 = readln()
    var P1 = Player(p1,"o")
    println("Second player's name:")
    var p2 = readln()
    var P2 = Player(p2,"*")
    var dim = ""
    var list = mutableListOf<String>()
    val scanner = Scanner(System.`in`)
    val regex = Regex("\\d+x?X?\\d+")
    val de = Regex("[xX]")
    do {


        println("Set the board dimensions (Rows x Columns)")
        println("Press Enter for default (6 x 7)")
        dim = readln()!!.replace("\\s".toRegex(), "")
        if (dim == "")
            dim = "6x7"
        list = dim.split(de).toMutableList()
        if (!regex.matches(dim)) {
            println("Invalid input")
        }
//        if(list[0] == "" || list[1] == "") {
//            println("Invalid input")
//        }
        else if (list[0].toInt() !in 5..9) {
            println("Board rows should be from 5 to 9")
        } else if (list[1].toInt() !in 5..9) {
            println("Board columns should be from 5 to 9")
        } else
            break
    } while (true)

    val reg = Regex("\\d")
    var g = ""
    do{
    println("Do you want to play single or multiple games?")
    println("For a single game, input 1 or press Enter")
    println("Input a number of games:")
    g = readln()!!
        if(g == "")
            break
        if(!reg.matches(g))
            println("Invalid input")
        else if(g.toInt()<=0)
            println("Invalid input")
        else  break

    }while(true)
        var games : Int = 0
    println("$p1 VS $p2")
    println(list[0] + " X " + list[1] + " board")
    if (g == "" || (reg.matches(g) && g.toInt() == 1)) {

        games = 1
    } else if (reg.matches(g) && g.toInt() > 1) {
        println("Total $g games")
        games = g.toInt()
    }
        var a = 1
        var b = 2
        var next = 0
    for (i in 1..games) {
        if (games == 1)
            println("Single game")
        else
            println("Game #$i")
        val r = list[0].toInt()
        val c = list[1].toInt()
        val board2D = MutableList(r) { MutableList(c) { " " } }
        drawBoard(r, c, board2D)
        while (true) {
            if(next == 0) {
                next = play(P1, r, board2D)
                if (next == 1)
                    P1.points += 2
                else if(next == 2){
                    P1.points +=1
                    P2.points +=1
                }
            }
            else
                break
            if(next == 0) {
                next = play(P2, r, board2D)
                if (next == 1)
                    P1.points += 2
                else if(next == 2){
                    P1.points +=1
                    P2.points +=1
                }

            }
            else break
        }
        if(games != 1)
        { println("Score")
        print("$p1: ")
            print(if(P1.name == p1)P1.points
                    else P2.points)
                        print(" $p2: ")
            println(if(P2.name == p2)P2.points
            else P1.points)
        P1 = P2.also { P2 = P1 }
        }
        //      a = b.also {b = a}
        next = 0
    }
    println("Game over!")

}