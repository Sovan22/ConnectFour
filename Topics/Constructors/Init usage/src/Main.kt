fun main() {
    val timerValue = readLine()!!.toInt()
    val timer = ByteTimer(timerValue)
    println(timer.time)
}

class ByteTimer(var _time: Int) {
    init {
        if(_time<-128)
            _time = -128
        else if(_time>127)
            _time = 127
    }
    val time = _time
}